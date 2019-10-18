package net.vortexdata.tsqpfp.welcomemessage;

import com.github.theholywaffle.teamspeak3.*;
import net.vortexdata.tsqpf.modules.*;
import net.vortexdata.tsqpf.plugins.*;

public class Config {

    PluginConfig config;

    public Config(PluginConfig config) {
        this.config = config;
        config.setDefault("messageWelcome", "Welcome to this Teamspeak server! There are currently %onlineUsers% users online.");
        config.saveAll();
    }

    public void load() {
        config.readAll();
    }

    public String getValue(String key) {
        return config.readValue(key);
    }

}
