package projects.splitwise.repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import projects.splitwise.model.Group;

public class InMemoryGroupRepository implements IGroupRepository {
    private final Map<String, Group> groups;

    public InMemoryGroupRepository() {
        this.groups = new ConcurrentHashMap<>();
    }

    @Override
    public Group findById(String id) {
        return this.groups.get(id);
    }

    @Override
    public void save(Group group) {
        this.groups.put(group.getId(), group);
    }
}
