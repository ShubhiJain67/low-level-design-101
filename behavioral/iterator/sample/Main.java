package behavioral.iterator.sample;

public class Main {

    public static void main(String[] args) {
        String[] songs = {
            "Song A",
            "Song B",
            "Song C"
        };
        Playlist playlist = new Playlist(songs);
        IIterator<String> iterator = playlist.createIterator();
        while (iterator.hasNext()) {
            String song = iterator.next();
            System.out.println(song);
        }
    }
}