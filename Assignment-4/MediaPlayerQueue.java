import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Set;

class Song {
    private final String title;
    private final String artist;

    Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    String getTitle() {
        return title;
    }

    String getArtist() {
        return artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Song)) {
            return false;
        }
        Song other = (Song) o;
        return title.equalsIgnoreCase(other.title) && artist.equalsIgnoreCase(other.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title.toLowerCase(), artist.toLowerCase());
    }

    @Override
    public String toString() {
        return title + " by " + artist;
    }
}

class PlaybackQueue {
    private static final int HISTORY_LIMIT = 5;

    private final LinkedList<Song> queue = new LinkedList<>();
    private final LinkedList<Song> history = new LinkedList<>();
    private ListIterator<Song> cursor = queue.listIterator();
    private Song current = null;
    private boolean lastMovedForward = true;

    void addLast(Song song) {
        queue.addLast(song);
        resetCursor();
    }

    void addToFront(Song song) {
        queue.addFirst(song);
        resetCursor();
    }

    void addAt(int position, Song song) {
        if (position < 0 || position > queue.size()) {
            throw new IndexOutOfBoundsException("Invalid position: " + position);
        }
        ListIterator<Song> it = queue.listIterator(position);
        it.add(song);
        resetCursor();
    }

    boolean remove(Song song) {
        boolean removed = queue.remove(song);
        if (removed) {
            if (song.equals(current)) {
                current = null;
            }
            resetCursor();
        }
        return removed;
    }

    int removeDuplicates() {
        Set<Song> seen = new HashSet<>();
        int removed = 0;
        Iterator<Song> it = queue.iterator();
        while (it.hasNext()) {
            if (!seen.add(it.next())) {
                it.remove();
                removed++;
            }
        }
        if (current != null && !queue.contains(current)) {
            current = null;
        }
        resetCursor();
        return removed;
    }

    private void resetCursor() {
        if (current == null) {
            cursor = queue.listIterator();
            lastMovedForward = true;
            return;
        }
        int index = 0;
        boolean found = false;
        for (Song s : queue) {
            if (s.equals(current)) {
                found = true;
                break;
            }
            index++;
        }
        if (found) {
            cursor = queue.listIterator(index + 1);
        } else {
            current = null;
            cursor = queue.listIterator();
        }
        lastMovedForward = true;
    }

    Song playNext() {
        if (lastMovedForward == false && cursor.hasNext()) {
            cursor.next();
        }
        if (!cursor.hasNext()) {
            lastMovedForward = true;
            return null;
        }
        current = cursor.next();
        lastMovedForward = true;
        recordHistory(current);
        return current;
    }

    Song playPrevious() {
        if (lastMovedForward == true && cursor.hasPrevious() && current != null) {
            cursor.previous();
        }
        if (!cursor.hasPrevious()) {
            lastMovedForward = false;
            return null;
        }
        current = cursor.previous();
        lastMovedForward = false;
        recordHistory(current);
        return current;
    }

    private void recordHistory(Song song) {
        history.addFirst(song);
        if (history.size() > HISTORY_LIMIT) {
            history.removeLast();
        }
    }

    Song getCurrent() {
        return current;
    }

    void printQueue() {
        System.out.println("Queue:");
        ListIterator<Song> it = queue.listIterator();
        while (it.hasNext()) {
            int position = it.nextIndex() + 1;
            System.out.println("  " + position + ". " + it.next());
        }
    }

    void printQueueReversed() {
        System.out.println("Queue (reverse):");
        ListIterator<Song> it = queue.listIterator(queue.size());
        while (it.hasPrevious()) {
            int position = it.previousIndex() + 1;
            System.out.println("  " + position + ". " + it.previous());
        }
    }

    void printHistory() {
        System.out.println("Recently played:");
        for (Song s : history) {
            System.out.println("  " + s);
        }
    }

    int size() {
        return queue.size();
    }
}

public class MediaPlayerQueue {
    public static void main(String[] args) {
        PlaybackQueue player = new PlaybackQueue();

        player.addLast(new Song("Believer", "Imagine Dragons"));
        player.addLast(new Song("Shape of You", "Ed Sheeran"));
        player.addLast(new Song("Believer", "Imagine Dragons"));
        player.addLast(new Song("Levitating", "Dua Lipa"));
        player.addLast(new Song("Shape of You", "Ed Sheeran"));

        player.addToFront(new Song("Blinding Lights", "The Weeknd"));
        player.addAt(2, new Song("Senorita", "Shawn Mendes"));

        player.printQueue();

        int removed = player.removeDuplicates();
        System.out.println("Duplicates removed: " + removed);
        player.printQueue();
        player.printQueueReversed();

        System.out.println("Now playing: " + player.playNext());
        System.out.println("Now playing: " + player.playNext());
        System.out.println("Now playing: " + player.playNext());
        System.out.println("Back to: " + player.playPrevious());
        System.out.println("Back to: " + player.playPrevious());
        System.out.println("Forward to: " + player.playNext());

        player.printHistory();
    }
}
