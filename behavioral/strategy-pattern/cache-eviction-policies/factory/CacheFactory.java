package factory;

import cache.Cache;
import evictionstrategies.LeastFrequentlyUsedStrategy;
import evictionstrategies.LeastRecentlyUsedStrategy;
import evictionstrategies.MostRecentlyUsedStrategy;
import storage.HashMapStorage;

public class CacheFactory {

    public static <K, V> Cache<K, V> createLRUCache(int capacity) {
        return new Cache<>(
                capacity,
                new HashMapStorage<>(),
                new LeastRecentlyUsedStrategy<>()
        );
    }

    public static <K, V> Cache<K, V> createLFUCache(int capacity) {
        return new Cache<>(
                capacity,
                new HashMapStorage<>(),
                new LeastFrequentlyUsedStrategy<>()
        );
    }

    public static <K, V> Cache<K, V> createMRUCache(int capacity) {
        return new Cache<>(
                capacity,
                new HashMapStorage<>(),
                new MostRecentlyUsedStrategy<>()
        );
    }
}
