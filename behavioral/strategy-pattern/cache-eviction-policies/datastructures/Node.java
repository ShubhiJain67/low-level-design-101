package datastructures;
public class Node<K> {

    public K key;
    public int frequency;

    Node<K> prev;
    Node<K> next;

    public Node(K key) {
        this.key = key;
        this.frequency = 1;
    }
}