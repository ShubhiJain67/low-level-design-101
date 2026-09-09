package behavioral.strategy.projects.cacheevictionpolicies.factory;

import behavioral.strategy.projects.cacheevictionpolicies.cache.Cache;
import behavioral.strategy.projects.cacheevictionpolicies.evictionstrategies.LeastFrequentlyUsedStrategy;
import behavioral.strategy.projects.cacheevictionpolicies.evictionstrategies.LeastRecentlyUsedStrategy;
import behavioral.strategy.projects.cacheevictionpolicies.evictionstrategies.MostRecentlyUsedStrategy;
import behavioral.strategy.projects.cacheevictionpolicies.storage.HashMapStorage;

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
