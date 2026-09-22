package projects.lrucache.model;

public class DoublyLinkedList<KeyT, ValueT> {

    private final int MAX_SIZE;
    private int size;
    private final Node<KeyT, ValueT> head;
    private final Node<KeyT, ValueT> tail;
    
    public DoublyLinkedList(int size){
        this.MAX_SIZE = size;
        this.size = 0;
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        head.setNext(tail);
        tail.setPrev(head);
    }

    public boolean hasSpace(){
        return this.size < this.MAX_SIZE;
    }

    public void remove(Node<KeyT, ValueT> node){
        if(this.size == 0){
            return;
        }
        Node<KeyT, ValueT> prev = node.getPrev();
        Node<KeyT, ValueT> next = node.getNext();
        prev.setNext(next);
        node.setPrev(null);
        next.setPrev(prev);
        node.setNext(null);
        this.size -= 1;
    }
    
    public Node<KeyT, ValueT> removeLast(){
        if(this.size == 0){
            return null;
        }
        Node<KeyT, ValueT> evicted = this.tail.getPrev();
        Node<KeyT, ValueT> prev = evicted.getPrev();
        prev.setNext(this.tail);
        this.tail.setPrev(prev);
        evicted.setNext(null);
        evicted.setPrev(null);
        this.size -= 1;
        return evicted;
    }

    public void addFirst(Node<KeyT, ValueT> node){
        node.setNext(this.head.getNext());
        node.setPrev(this.head);
        this.head.getNext().setPrev(node);
        this.head.setNext(node);
        this.size += 1;
    }
    
    public void print(){
        Node<KeyT, ValueT> ptr = this.head;
        while(ptr != null){
            if(ptr.getKey() != null){
                System.out.print(ptr.getKey()+":"+ptr.getValue()+" -> ");
            }
            ptr = ptr.getNext();
        }
        System.out.println("with size " + this.size);
    }
}
