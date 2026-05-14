package evictionstrategies;
import datastructures.DoublyLinkedList;
import datastructures.Node;
import java.util.HashMap;
import java.util.Map;

public class MostRecentlyUsedStrategy<K> implements IEvictionStrategy<K> {

    private final DoublyLinkedList<K> store;
    private final Map<K, Node<K>> nodeMap;

    public MostRecentlyUsedStrategy() {
        this.store = new DoublyLinkedList<>();
        this.nodeMap = new HashMap<>();
    }

    @Override
    public void onGet(K key) {
        Node<K> node = nodeMap.get(key);
        if (node != null) {
            store.moveToFront(node);
        }
    }

    @Override
    public void onPut(K key) {
        if (nodeMap.containsKey(key)) {
            store.moveToFront(nodeMap.get(key));
        } else {
            Node<K> node = new Node<>(key);
            nodeMap.put(key, node);
            store.addFirst(node);
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
        Node<K> first = store.removeFirst();
        if (first != null) {
            nodeMap.remove(first.key);
            return first.key;
        }
        return null;
    }
}
