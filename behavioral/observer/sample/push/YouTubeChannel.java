package behavioral.observer.sample.push;

import java.util.ArrayList;
import java.util.List;

public class YouTubeChannel implements ISubject {
    private List<IObserver> subscribers = new ArrayList<>();
    private String latestVideo;

    public YouTubeChannel(){}

    @Override
    public void subscribe(IObserver observer) {
        this.subscribers.add(observer);
    }

    @Override
    public void unsubscribe(IObserver observer) {
        this.subscribers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : this.subscribers) {
            observer.update(this.latestVideo);
        }
    }

    public void uploadVideo(String video) {
        this.latestVideo = video;
        System.out.println("Uploaded: " + video);
        notifyObservers();
    }
}
