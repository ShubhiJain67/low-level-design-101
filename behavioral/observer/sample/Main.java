package behavioral.observer.sample;

import behavioral.observer.sample.subjects.YouTubeChannel;

public class Main {

    public static void main(String[] args) {
        YouTubeChannel yourtechbuddy = new YouTubeChannel();

        IObserver shubhi = new User("Shubhi");
        IObserver prateek = new User("Prateek");

        yourtechbuddy.subscribe(shubhi);
        yourtechbuddy.uploadVideo("Strategy Design Pattern 1");
        yourtechbuddy.subscribe(prateek);
        yourtechbuddy.uploadVideo("Observer Design Pattern 2");
    }
}
