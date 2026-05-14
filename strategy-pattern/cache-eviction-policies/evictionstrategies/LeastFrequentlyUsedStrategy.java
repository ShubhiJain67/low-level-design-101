package evictionstrategies;
import datastructures.Node;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LeastFrequentlyUsedStrategy<K> implements IEvictionStrategy<K> {

    private final PriorityQueue<Node<K>> store;
    private final Map<K, Node<K>> nodeMap;

    public LeastFrequentlyUsedStrategy() {
        this.store = new PriorityQueue<>((a, b) -> Integer.compare(a.frequency, b.frequency));
        this.nodeMap = new HashMap<>();
    }

    @Override
    public void onGet(K key) {
        Node<K> node = nodeMap.get(key);
        node.frequency++;
        store.remove(node);
        store.offer(node);
    }

    @Override
    public void onPut(K key) {
        if (nodeMap.containsKey(key)) {
            Node<K> node = nodeMap.get(key);
            node.frequency++;
            store.remove(node);
            store.offer(node);
        } else {
            Node<K> node = new Node<>(key);
            nodeMap.put(key, node);
            store.offer(node);
        }
    }

    @Override
    public void onDelete(K key) {
        Node<K> node = nodeMap.remove(key);
        if (node != null) {
            store.remove(node);
        }
    }

    @Override
    public K evict() {
        Node<K> node = store.poll();
        if (node != null) {
            nodeMap.remove(node.key);
            return node.key;
        }
        return null;
    }
}
