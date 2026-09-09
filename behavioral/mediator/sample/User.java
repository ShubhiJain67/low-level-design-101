package behavioral.mediator.sample;

abstract class User {
    protected IChatMediator mediator;
    protected String name;

    public User(IChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void send(String message);

    public abstract void receive(String message);
}
