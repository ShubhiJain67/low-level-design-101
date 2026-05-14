package cache;

public interface ICache<K, V> {
    V get(K key);
    void put(K key, V value);
    void delete(K key);
}
