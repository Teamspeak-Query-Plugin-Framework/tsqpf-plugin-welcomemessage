package net.vortexdata.tsqpf_plugin_welcomemessage.cache;

public class UpdateTask implements Runnable {

    private Cache cache;

    public UpdateTask(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void run() {
        cache.updateAllValues();
    }

}
