package net.vortexdata.tsqpf_plugin_welcomemessage.cache.values;

import com.github.theholywaffle.teamspeak3.*;
import com.github.theholywaffle.teamspeak3.api.wrapper.*;
import net.vortexdata.tsqpf.plugins.*;
import net.vortexdata.tsqpf_plugin_welcomemessage.cache.*;

import java.time.*;
import java.time.format.*;
import java.util.*;

public class CVLastIssueBanDate extends CachedValue {

    public CVLastIssueBanDate(TS3Api api) {
        super("lastIssuedBanDate", "0", api);
    }

    @Override
    public void update() {
        ArrayList<Ban> bans = new ArrayList<>(api.getBans());

        LocalDateTime newest = null;
        for (Ban current : bans) {
            LocalDateTime ldt = current.getCreatedDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            if (newest == null)
                newest = ldt;
            else
                if (ldt.isBefore(newest))
                    newest = ldt;
        }

        if (newest != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, YYYY - HH:mm:ss");
            value = formatter.format(newest);
        }
    }

}
