package clustering.blogs;

import java.util.HashMap;
import java.util.Map;

public class Blog {
    private String title;
    Map<String, Double> wordFrequencies = new HashMap<>();
    Blog(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void addWord(String word, Double frequency) {
        wordFrequencies.put(word, frequency);
    }
}
