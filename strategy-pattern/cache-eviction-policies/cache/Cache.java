package cache;

import evictionstrategies.IEvictionStrategy;
import storage.IStorage;

public class Cache<K, V> implements ICache<K, V>{
    private final int capacity;
    private final IStorage<K, V> storage;
    private final IEvictionStrategy<K> evictionStrategy;

    public Cache(int capacity, IStorage<K, V> storage, IEvictionStrategy<K> evictionStrategy) {
        this.capacity = capacity;
        this.storage = storage;
        this.evictionStrategy = evictionStrategy;
    }

    @Override
    public V get(K key){
        if (storage!= null && storage.contains(key)) {
            V value = storage.get(key);
            evictionStrategy.onGet(key);
            return value;
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        if (storage.size() >= capacity) {
            K evictedKey = evictionStrategy.evict();
            storage.remove(evictedKey);
        }
        storage.put(key, value);
        evictionStrategy.onPut(key);
    }

    @Override
    public void delete(K key) {
        if (storage.contains(key)) {
            storage.remove(key);
            evictionStrategy.onDelete(key);
        }
    }
}
