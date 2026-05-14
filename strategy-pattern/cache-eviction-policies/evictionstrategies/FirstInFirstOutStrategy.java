package evictionstrategies;

public class FirstInFirstOutStrategy<K> implements IEvictionStrategy<K>  {
    private final java.util.Queue<K> store;

    public FirstInFirstOutStrategy() {
        this.store = new java.util.LinkedList<>();
    }

    @Override
    public void onGet(K key) {
        // No action needed for FIFO on get
    }

    @Override
    public void onPut(K key) {
        store.offer(key);
    }

    @Override
    public void onDelete(K key) {
        store.remove(key);
    }

    @Override
    public K evict() {
        return store.poll();
    }
    
}
