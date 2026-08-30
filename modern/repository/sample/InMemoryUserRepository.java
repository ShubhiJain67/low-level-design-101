package modern.repository.sample;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * One concrete storage technology behind UserRepository — here, an in-memory
 * Map standing in for a real database. Swap this class for
 * JdbcUserRepository / MongoUserRepository / a fake-for-tests later, and
 * nothing in UserService changes, because UserService never named this
 * class — only the UserRepository interface.
 */
public class InMemoryUserRepository implements IUserRepository {
    private final Map<Integer, User> store = new LinkedHashMap<>();

    @Override
    public void save(User user) {
        store.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(int id) {
        store.remove(id);
    }
}
