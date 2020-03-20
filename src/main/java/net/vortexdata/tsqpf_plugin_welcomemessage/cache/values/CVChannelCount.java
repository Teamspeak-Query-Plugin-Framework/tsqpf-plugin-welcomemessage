package net.vortexdata.tsqpf_plugin_welcomemessage.cache.values;

import com.github.theholywaffle.teamspeak3.*;
import net.vortexdata.tsqpf_plugin_welcomemessage.cache.*;

public class CVChannelCount extends CachedValue {

    public CVChannelCount(TS3Api api) {
        super("channelCount", "0", api);
    }

    @Override
    public void update() {
        value = Integer.toString(api.getChannels().size());
    }

}
