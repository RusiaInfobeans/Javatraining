package  Task;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HomeWork {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> li2 = new ArrayList<>();
        List<String> li3 = new ArrayList<>();
        list.add("pop");
        list.add("rock");

        li2.add("pop");

        li3.add("rock");

        Song[] songs = {new Song("Rang", 5, list), new Song("sufi", 6, li2), new Song("Gazal", 4, li3)
        };
        Map<String,Integer> gener = new LinkedHashMap<>(Song.maximumOfGenres(songs));
        String[] s = new String[2];
        s[0] = "pop : ";
        s[1] = "Rock: ";
        int count = 0;
        for (Map.Entry<String, Integer> entry : gener.entrySet()) {
            System.out.println(s[count] + entry.getKey() +
                    ", Value = " + entry.getValue());
            count++;
        }

    }

}

