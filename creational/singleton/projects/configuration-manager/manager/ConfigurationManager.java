package manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import loader.ConfigLoader;
import printer.Printer;

public final class ConfigurationManager {

    private final Map<String, String> configs;
    private final ReadWriteLock lock;

    private ConfigurationManager() {
        this.configs = new ConcurrentHashMap<>();
        this.lock = new ReentrantReadWriteLock();
        loadConfigs();
    }

    // Bill Pugh Singleton
    private static class Holder {
        private static final ConfigurationManager INSTANCE = new ConfigurationManager();
    }

    public static ConfigurationManager getInstance() {
        return Holder.INSTANCE;
    }

    private void loadConfigs() {
        Printer.print("Loading configs...");
        lock.writeLock().lock();
        try {
            ConfigLoader loader = new ConfigLoader();
            configs.clear();
            configs.putAll(loader.load());
        } catch (Exception e) {
            Printer.print("ERROR -> " + e.getMessage());
        } finally {
            Printer.print("Unlocking write lock after loading configs");
            lock.writeLock().unlock();
        }
        Printer.print("Configs loaded: " + configs);
    }

    // Read config
    public String get(String key) {
        Printer.print("Acquiring read lock for key: " + key);
        lock.readLock().lock();
        try {
            return configs.get(key);
        } catch (Exception e) {
            Printer.print("ERROR -> " + e.getMessage());
        } finally {
            Printer.print("Unlocking read lock for key: " + key);
            lock.readLock().unlock();
        }
        return null;
    }
}
