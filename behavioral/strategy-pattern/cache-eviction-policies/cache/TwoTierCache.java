package cache;

public class TwoTierCache<K, V> implements ICache<K, V> {

    private final ICache<K, V> l1Cache;
    private final ICache<K, V> l2Cache;

    public TwoTierCache(ICache<K, V> l1Cache, ICache<K, V> l2Cache) {
        this.l1Cache = l1Cache; // Faster, smaller cache
        this.l2Cache = l2Cache; // Slower, larger cache
    }

    @Override
    public V get(K key) {
        V value = l1Cache.get(key);
        if (value != null) {
            System.out.println("L1 Cache Hit for key: " + key);
            return value;
        }
        System.out.println("L1 Cache Miss for key: " + key);
        value = l2Cache.get(key);
        if (value != null) {
            System.out.println("L2 Cache Hit for key: " + key);

            l1Cache.put(key, value);
            return value;
        }
        System.out.println("L2 Cache Miss for key: " + key);
        return null;
    }

    @Override
    public void put(K key, V value) {
        l1Cache.put(key, value);
        l2Cache.put(key, value);
    }

    @Override
    public void delete(K key) {
        l1Cache.delete(key);
        l2Cache.delete(key);
    }
}
