package evictionstrategies;

public class RandomReplacementStrategy<K> implements IEvictionStrategy<K> {
    private final java.util.List<K> store;
    private final java.util.Random random;

    public RandomReplacementStrategy() {
        this.store = new java.util.ArrayList<>();
        this.random = new java.util.Random();
    }

    @Override
    public void onGet(K key) {
        // No action needed for Random Replacement on get
    }

    @Override
    public void onPut(K key) {
        store.add(key);
    }

    @Override
    public void onDelete(K key) {
        store.remove(key);
    }

    @Override
    public K evict() {
        if (store.isEmpty()) {
            return null; // No entries to evict
        }
        int index = random.nextInt(store.size());
        K keyToEvict = store.get(index);
        store.remove(index);
        return keyToEvict;
    }
    
}
