package Task;

import java.util.*;
import java.util.stream.Collectors;

public class MaxSongPlayed {


    public static void genreMaxPlayedSong(List<Song3> songs) {
        HashMap<String, Song3> map = new HashMap<>();
        for (Song3 song : songs) {
            List<String> genres = song.getGenre();
            for (String genre : genres) {
                if (map.containsKey(genre)) {
                    Song3 preSong = map.get(genre);
                    if (preSong.getPlayCount() < song.getPlayCount()) {
                        map.put(genre, song);
                    }
                } else {
                    map.put(genre, song);
                }
            }
        }
        Set<Map.Entry<String, Song3>> entries = map.entrySet();
        for (Map.Entry entry : entries) {
            Song3 song = (Song3) entry.getValue();
            System.out.println(entry.getKey() + " -> " + song.getTitle() + "," + song.getPlayCount());
        }
    }

    private static HashMap<String, List<Song3>> genreN_MaxPlayedSong(List<Song3> songs, long n) {
        HashMap<String, List<Song3>> genreSongsMap = new HashMap<>();
        for (Song3 song : songs) {
            List<String> genres = song.getGenre();
            for (String genre : genres) {
                if (genreSongsMap.containsKey(genre)) {
                    List<Song3> songs1 = new ArrayList<>(genreSongsMap.get(genre));
                    songs1.add(song);
                    genreSongsMap.put(genre, songs1.stream().sorted((s1, s2) -> s2.getPlayCount() - s1.getPlayCount()).limit(n).collect(Collectors.toList()));
                } else {
                    genreSongsMap.put(genre, Arrays.asList(song));
                }
            }
        }
        return genreSongsMap;
    }

    public static void main(String[] args) {
        Song3 song_1 = new Song3("A", 1, Arrays.asList("pop"));
        Song3 song_2 = new Song3("B", 2, Arrays.asList("rock"));
        Song3 song_3 = new Song3("C", 8, Arrays.asList("pop"));
        Song3 song_4 = new Song3("D", 9, Arrays.asList("pop"));
        Song3 song_5 = new Song3("E", 6, Arrays.asList("rock"));
        Song3 song_6 = new Song3("F", 10, Arrays.asList("rock", "classic", "pop"));
        List<Song3> songs = Arrays.asList(song_1, song_2, song_3, song_4, song_5, song_6);
        HashMap<String, List<Song3>> map = genreN_MaxPlayedSong(songs, 2);
        Set<Map.Entry<String, List<Song3>>> entries = map.entrySet();
        for (Map.Entry entry : entries) {
            List<Song3> value = (List<Song3>) entry.getValue();
            String key = (String) entry.getKey();
            System.out.print(" " + key + " ->");
            for (Song3 song : value) {
                System.out.print(" " + song.getTitle() + "  " + song.getPlayCount() + " |");
            }
            System.out.println();
        }
    }
}

class Song3 {
    private String title;
    private int playCount;
    private List<String> genre;

    public Song3() {
    }

    public Song3(String title, int playCount, List<String> genre) {
        this.title = title;
        this.playCount = playCount;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Song{" + "title='" + title + '\'' + ", playCount=" + playCount + ", genre=" + genre + '}';
    }

}
