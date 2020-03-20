package net.vortexdata.tsqpf_plugin_welcomemessage.cache.values;

import com.github.theholywaffle.teamspeak3.*;
import com.github.theholywaffle.teamspeak3.api.wrapper.*;
import net.vortexdata.tsqpf.plugins.*;
import net.vortexdata.tsqpf_plugin_welcomemessage.cache.*;

import java.util.*;

public class CVBannedClientCount extends CachedValue {

    private PluginConfig config;

    public CVBannedClientCount(TS3Api api) {
        super("bannedClientCount", "0", api);
    }

    @Override
    public void update() {
        value = Integer.toString(api.getBans().size());
    }

}
