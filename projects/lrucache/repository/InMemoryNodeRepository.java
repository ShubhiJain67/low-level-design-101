package projects.lrucache.repository;

import java.util.concurrent.ConcurrentHashMap;
import projects.lrucache.model.Node;

public class InMemoryNodeRepository<KeyT, ValueT> implements INodeRepository<KeyT, ValueT> {
    private final ConcurrentHashMap<KeyT, Node<KeyT, ValueT>> store;

    public InMemoryNodeRepository(){
        this.store = new ConcurrentHashMap<>();
    }

    @Override
    public Node<KeyT, ValueT> get(KeyT key) {
        return this.store.get(key);
    }

    @Override
    public void put(Node<KeyT, ValueT> node){
        this.store.put(node.getKey(), node);
    }

    @Override
    public void delete(Node<KeyT, ValueT> node){
        this.store.remove(node.getKey());
    }
}
