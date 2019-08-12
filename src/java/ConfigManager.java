import net.vortexdata.tsqpf.modules.*;

import java.util.*;

public class ConfigManager {

    HashMap<String, String> defaultValues;
    PluginConfig config;

    public ConfigManager(PluginConfig config) {
        defaultValues = new HashMap<>();
        defaultValues.put("messageWelcome", "Welcome to this Teamspeak server!");
        this.config = config;
    }

    public void load() {
        config.readAll();
        Object[] defaultKeys = defaultValues.keySet().toArray();

        for (int i = 0; i < defaultKeys.length; i++) {
            if (config.readValue(defaultKeys[i].toString()) == null || config.readValue(defaultKeys[i].toString()).equals("")) {
                createConfig(defaultKeys);
            }
        }
    }

    private void createConfig(Object[] keys) {
        config.clear();

        for (int i = 0; i < keys.length; i++) {
            config.setDefault(keys[i].toString(), defaultValues.get(keys[i].toString()));
        }

        config.saveAll();
    }

    public String getValue(String key) {
        return config.readValue(key);
    }

}
