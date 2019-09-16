package spartanconfigapi;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import spartanconfigapi.annotations.Config;
import spartanconfigapi.annotations.ConfigValue;
import spartanconfigapi.exceptions.IncompatibleTypeException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ConfigBuilder {
    /**
     * @author Pedro Cavalcanti (zKingOfKill)
     * @version 1.0
     * @category Utils
     */

    private Object object;
    private File file;
    private Class clazz;
    private FileConfiguration fileConfiguration;

    public void init(Plugin plugin, Object object) throws IncompatibleTypeException {
        this.object = object;
        clazz = super.getClass();
        if (clazz.isAnnotationPresent(Config.class)) {
            String fileName = ((Config) clazz.getAnnotation(Config.class)).path();
            file = new File(plugin.getDataFolder(), fileName);
            fileConfiguration = YamlConfiguration.loadConfiguration(file);
            saveConfig();
        }
        throw new IncompatibleTypeException();
    }

    public void saveConfig() {
        List<Field> fields = getFieldList(clazz);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        updateFields(object, file, fileConfiguration, fields);
    }

    private List<Field> getFieldList(Class clazz) {
        List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
        fields.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
        return fields;
    }

    public <T> T parse(Class<T> clazz, File file) throws IncompatibleTypeException, InstantiationException, IllegalAccessException {
        T object = clazz.newInstance();
        if (clazz.isAnnotationPresent(Config.class)) {
            List<Field> fields = getFieldList(clazz);
            YamlConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
            updateFields(object, file, fileConfiguration, fields);
            return ((T) object);
        }
        throw new IncompatibleTypeException();
    }

    private void updateFields(Object object, File file, FileConfiguration fileConfiguration, List<Field> fields) {
        for (Field field : fields) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            String fieldpath = field.getAnnotation(ConfigValue.class).path();
            if (fieldpath.isEmpty()) {
                fieldpath = field.getName();
            }
            Object fieldvalue = null;
            boolean translateAlternativeColors = field.getAnnotation(ConfigValue.class).translateAlternativeColors();
            try {
                fieldvalue = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (!fileConfiguration.contains(fieldpath)) {
                fileConfiguration.set(fieldpath, fieldvalue);
                try {
                    fileConfiguration.save(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Object value = fileConfiguration.get(fieldpath);
                if (translateAlternativeColors) {
                    if (value instanceof String) {
                        value = ((String) fieldvalue).replace("&", "§");
                    }
                    if (value instanceof List) {
                        value = ((List<String>) fieldvalue).stream().map(o -> o.contains("&") ? o.replace("&", "§") : o).collect(Collectors.toList());
                    }

                }
                field.set(object, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void addVariable(List<String> list, String var, Object replacement) {
        list.stream().map(o -> o.contains(var) ? o.replace(var, replacement.toString()) : o).collect(Collectors.toList());
    }
    public void sendAll(List<String> msg) {
        Bukkit.getOnlinePlayers().forEach(player -> msg.forEach(m -> player.sendMessage(m)));
    }
    public void sendAll(String msg) {
        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(msg));
    }
}
