package evictionstrategies;

public class TimeToLiveBasedStrategy<K> implements IEvictionStrategy<K> {
    private final java.util.Map<K, Long> store;
    private final long ttlInMillis;

    public TimeToLiveBasedStrategy(long ttlInMillis) {
        this.store = new java.util.HashMap<>();
        this.ttlInMillis = ttlInMillis;
    }

    @Override
    public void onGet(K key) {
        // No action needed for TTL on get
    }

    @Override
    public void onPut(K key) {
        store.put(key, System.currentTimeMillis());
    }

    @Override
    public void onDelete(K key) {
        store.remove(key);
    }

    @Override
    public K evict() {
        long currentTime = System.currentTimeMillis();
        for (java.util.Map.Entry<K, Long> entry : store.entrySet()) {
            if (currentTime - entry.getValue() >= ttlInMillis) {
                K keyToEvict = entry.getKey();
                store.remove(keyToEvict);
                return keyToEvict;
            }
        }
        return null; // No expired entries found
    }
    
}
