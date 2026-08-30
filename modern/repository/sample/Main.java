package modern.repository.sample;

public class Main {
    public static void main(String[] args) {
        // Only Main (the composition root) knows the concrete
        // InMemoryUserRepository exists — UserService only ever sees the
        // UserRepository interface.
        IUserRepository repository = new InMemoryUserRepository();
        UserService userService = new UserService(repository);

        userService.registerUser(1, "Shubhi");
        userService.registerUser(2, "Prateek");

        System.out.println("All users: " + userService.listUsers());
        System.out.println("Lookup id=1: " + userService.getUser(1));

        userService.removeUser(2);
        System.out.println("After removing id=2: " + userService.listUsers());
    }
}
 