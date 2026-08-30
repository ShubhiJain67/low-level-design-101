package modern.repository.sample;

import java.util.List;

/**
 * Business logic layer. Depends on the UserRepository INTERFACE — injected
 * via the constructor (this is Repository + Dependency Injection working
 * together, the way they show up in almost every real layered codebase).
 *
 * Nothing in this class would change if InMemoryUserRepository were replaced
 * by a real database-backed implementation.
 */
public class UserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(int id, String name) {
        userRepository.save(new User(id, name));
    }

    public User getUser(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No user with id " + id));
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void removeUser(int id) {
        userRepository.deleteById(id);
    }
}
