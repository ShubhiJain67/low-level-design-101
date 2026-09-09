package behavioral.mediator.sample;

public interface IChatMediator {
    void sendMessage(String message, User sender);
    void addUser(User user);
}
