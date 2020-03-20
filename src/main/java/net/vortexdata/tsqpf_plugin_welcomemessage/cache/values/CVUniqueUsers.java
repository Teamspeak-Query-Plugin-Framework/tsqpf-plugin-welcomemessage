package net.vortexdata.tsqpf_plugin_welcomemessage.cache.values;

import com.github.theholywaffle.teamspeak3.*;
import net.vortexdata.tsqpf_plugin_welcomemessage.cache.*;

public class CVUniqueUsers extends CachedValue {

    public CVUniqueUsers(TS3Api api) {
        super("totalUniqueUsers", "0", api);
    }

    @Override
    public void update() {
        value = Integer.toString(api.getDatabaseClients().size());
    }

}
