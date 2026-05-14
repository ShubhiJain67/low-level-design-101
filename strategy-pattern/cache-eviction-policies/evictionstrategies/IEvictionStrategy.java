package evictionstrategies;
public interface IEvictionStrategy<K> {

    void onGet(K key);

    void onPut(K key);

    void onDelete(K key);

    K evict();
}