package projects.lrucache.service;

import projects.lrucache.model.DoublyLinkedList;
import projects.lrucache.model.Node;
import projects.lrucache.repository.INodeRepository;

public class CacheService<KeyT, ValueT> {
    private final INodeRepository<KeyT, ValueT> repository;
    private final DoublyLinkedList<KeyT, ValueT> list;

    public CacheService(INodeRepository<KeyT, ValueT> repository, int size) {
        this.repository = repository;
        list = new DoublyLinkedList<>(size);
    }

    public ValueT get(KeyT key){
        if(key == null){
            throw new IllegalArgumentException("Key cannot be null");
        }
        Node<KeyT,ValueT> node = repository.get(key);
        if(node == null){
            return null;
        }
        list.remove(node);
        list.addFirst(node);
        return node.getValue();
    }
    
    public void put(KeyT key, ValueT value){
        if(key == null){
            throw new IllegalArgumentException("Key cannot be null");
        }
        Node<KeyT,ValueT> node = repository.get(key);
        if(node == null) {
            node = new Node<>(key, value);
        } else {
            node.setValue(value);
            list.remove(node);
        }
        if(!list.hasSpace()){
            Node<KeyT, ValueT> last = list.removeLast();
            System.err.println("Did not have size removing last element " + last.getKey());
            repository.delete(last);
        }
        list.addFirst(node);
        repository.put(node);
    }

    public void delete(KeyT key){
        if(key == null){
            throw new IllegalArgumentException("Key cannot be null");
        }
        Node<KeyT,ValueT> node = repository.get(key);
        if(node == null) {
            return;
        } else {
            list.remove(node);
        }
        repository.delete(node);
    }

    public void print(){
        list.print();
    }
}
