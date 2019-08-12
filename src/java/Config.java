import com.github.theholywaffle.teamspeak3.*;
import net.vortexdata.tsqpf.modules.*;

public class Config {

    PluginConfig config;

    public Config(PluginConfig config) {
        this.config = config;
        config.setDefault("messageWelcome", "Welcome to this Teamspeak server! There are currently %onlineUsers% users online.");
    }

    public void load() {
        config.readAll();
    }

    public String getValue(String key) {
        return config.readValue(key);
    }

}
