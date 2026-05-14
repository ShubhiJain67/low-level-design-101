package datastructures;
public class DoublyLinkedList<K> {

    Node<K> head;
    Node<K> tail;

    public DoublyLinkedList() {

        head = new Node<>(null);
        tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
    }

    public void addFirst(Node<K> node) {

        Node<K> nextNode = head.next;

        head.next = node;
        node.prev = head;

        node.next = nextNode;
        nextNode.prev = node;
    }

    public void remove(Node<K> node) {
        Node<K> prevNode = node.prev;
        Node<K> nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    public void moveToFront(Node<K> node) {
        remove(node);
        addFirst(node);
    }

    public Node<K> removeLast() {
        if(tail.prev == head) {
            return null;
        }
        Node<K> lastNode = tail.prev;
        remove(lastNode);
        return lastNode;
    }

    public Node<K> removeFirst() {
        if(head.next == tail) {
            return null;
        }
        Node<K> firstNode = head.next;
        remove(firstNode);
        return firstNode;
    }
}