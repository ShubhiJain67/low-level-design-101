package behavioral.observer.sample.push;

public class User implements IObserver {

    private final String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String video) {
        System.out.println(
            name + " received notification: New video - " + video
        );
    }
}
