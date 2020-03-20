package net.vortexdata.tsqpf_plugin_welcomemessage.cache.values;

import com.github.theholywaffle.teamspeak3.*;
import net.vortexdata.tsqpf_plugin_welcomemessage.cache.*;

public class CVChannelGroupCount extends CachedValue {

    public CVChannelGroupCount(TS3Api api) {
        super("channelgroupCount", "0", api);
    }

    @Override
    public void update() {
        value = Integer.toString(api.getChannelGroups().size());
    }

}
