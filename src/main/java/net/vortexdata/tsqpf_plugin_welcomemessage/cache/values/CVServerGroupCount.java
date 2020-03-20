package net.vortexdata.tsqpf_plugin_welcomemessage.cache.values;

import com.github.theholywaffle.teamspeak3.*;
import net.vortexdata.tsqpf_plugin_welcomemessage.cache.*;

public class CVServerGroupCount extends CachedValue {

    public CVServerGroupCount(TS3Api api) {
        super("servergroupCount", "0", api);
    }

    @Override
    public void update() {
        value = Integer.toString(api.getServerGroups().size());
    }

}
