package behavioral.iterator.sample;

public class PlaylistIterator implements IIterator<String> {
    private String[] songs;
    private int index = 0;

    public PlaylistIterator(String[] songs) {
        this.songs = songs;
    }

    @Override
    public boolean hasNext() {
        return index < songs.length;
    }

    @Override
    public String next() {
        String currentSong = songs[index];
        index++;
        return currentSong;
    }
}
