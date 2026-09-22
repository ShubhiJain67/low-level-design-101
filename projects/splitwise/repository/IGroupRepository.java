package projects.splitwise.repository;

import projects.splitwise.model.Group;

public interface IGroupRepository {
    Group findById(String id);
    void save(Group group);
}
