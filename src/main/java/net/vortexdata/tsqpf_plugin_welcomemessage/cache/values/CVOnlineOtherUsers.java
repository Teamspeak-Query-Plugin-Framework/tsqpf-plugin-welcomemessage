package net.vortexdata.tsqpf_plugin_welcomemessage.cache.values;

import com.github.theholywaffle.teamspeak3.*;
import net.vortexdata.tsqpf_plugin_welcomemessage.cache.*;

public class CVOnlineOtherUsers extends CachedValue {

    private CVOnlineUsers cvOnlineUsers;

    public CVOnlineOtherUsers(TS3Api api, CVOnlineUsers cvOnlineUsers) {
        super("onlineOtherUsers", "0", api);
        this.cvOnlineUsers = cvOnlineUsers;
    }

    @Override
    public void update() {
        value = Integer.toString(cvOnlineUsers.getTotalOtherUsers());
    }

}
