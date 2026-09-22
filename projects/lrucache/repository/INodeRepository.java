package projects.lrucache.repository;

import projects.lrucache.model.Node;

public interface INodeRepository<KeyT, ValueT> {
    Node<KeyT, ValueT> get(KeyT key);
    void put(Node<KeyT, ValueT> node);
    void delete(Node<KeyT, ValueT> node);
}
