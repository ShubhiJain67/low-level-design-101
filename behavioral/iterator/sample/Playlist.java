package behavioral.iterator.sample;

public class Playlist {
    private final String[] songs;

    public Playlist(String[] songs) {
        this.songs = songs;
    }

    public IIterator<String> createIterator() {
        return new PlaylistIterator(songs);
    }
}