package modern.repository.sample;

import java.util.List;
import java.util.Optional;

/**
 * Mediates between the domain and the data-mapping layer using a
 * collection-like interface for accessing domain objects (Fowler, PoEAA).
 *
 * The business layer (UserService) depends only on THIS interface — it never
 * sees SQL, an ORM, a file path, or a network call. Any storage technology
 * can sit behind it as long as it implements these 4 methods.
 */
public interface IUserRepository {
    void save(User user);
    Optional<User> findById(int id);
    List<User> findAll();
    void deleteById(int id);
}
