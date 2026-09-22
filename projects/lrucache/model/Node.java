package projects.lrucache.model;

public class Node<KeyT, ValueT> {
    private final KeyT key;
    private ValueT value;
    private Node<KeyT, ValueT> prev;
    private Node<KeyT, ValueT> next;

    public Node(KeyT key, ValueT value){
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    } 

    public KeyT getKey(){
        return this.key;
    }

    public ValueT getValue(){
        return this.value;
    }

    public Node<KeyT, ValueT> getNext(){
        return this.next;
    }

    public Node<KeyT, ValueT> getPrev(){
        return this.prev;
    }

    public void setPrev(Node<KeyT, ValueT> prev){
        this.prev = prev;
    }

    public void setNext(Node<KeyT, ValueT> next){
        this.next = next;
    }

    public void setValue(ValueT value){
        this.value = value;
    }
}
