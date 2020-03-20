package net.vortexdata.tsqpf_plugin_welcomemessage.cache.values;

import com.github.theholywaffle.teamspeak3.*;
import net.vortexdata.tsqpf_plugin_welcomemessage.cache.*;

public class CVOnlineUsers extends CachedValue {

    public CVOnlineUsers(TS3Api api) {
        super("onlineUsers", "0", api);
    }

    @Override
    public void update() {
        value = Integer.toString(api.getServerInfo().getClientsOnline());
    }

    public int getTotalUsers() {
        return Integer.parseInt(value);
    }

    public int getTotalOtherUsers() {
        return getTotalUsers() -1;
    }

}
