package Task;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Song {
    private final String title;
    private final int playCount;
    private final List<String> genre;
    public static int maxRock = 0;
    public static int maxPop;
    private static String maxRockTitle;
    private static String maxPopTitle;

    public Song(String title, int playCount, List<String> genre) {
        this.title = title;
        this.playCount = playCount;
        this.genre = genre;

    }

    public String getTitle() {
        return title;
    }

    private int getPlayCount() {
        return playCount;
    }

    public List<String> getGenre() {
        return genre;
    }


    @Override
    public String toString() {
        return ("title= " + title + " " + " count =" + playCount);
    }

    public static Map<String, Integer> maximumOfGenres(Song[] o) {

        Map<String, Integer> map = new LinkedHashMap<>();
        for (Song songs : o) {

            if (songs.getGenre().contains("rock")) {
                if (songs.playCount > maxRock) {
                    maxRock = songs.playCount;
                    maxRockTitle = songs.title;
                }
            }
            if (songs.getGenre().contains("pop")) {
                if (songs.playCount > maxPop) {
                    maxPop = songs.playCount;
                    maxPopTitle = songs.title;
                }
            }
        }
        map.put(maxPopTitle, maxPop);
        map.put(maxRockTitle, maxRock);
        return map;
    }
}