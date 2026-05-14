package evictionstrategies;
import datastructures.DoublyLinkedList;
import datastructures.Node;
import java.util.HashMap;
import java.util.Map;

public class LeastRecentlyUsedStrategy<K> implements IEvictionStrategy<K> {

    private final DoublyLinkedList<K> store;
    private final Map<K, Node<K>> nodeMap;

    public LeastRecentlyUsedStrategy() {
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
        Node<K> last = store.removeLast();
        if (last != null) {
            nodeMap.remove(last.key);
            return last.key;
        }
        return null;
    }
}
