package clustering.blogs;

import java.util.HashMap;
import java.util.Map;

class Blog {
    private String title;
    private Map<String, Integer> wordFrequencies = new HashMap<>();
    Blog(String title) {
        this.title = title;
    }

    String getTitle() {
        return title;
    }

    Map<String, Integer> getWordFrequencies() {
        return wordFrequencies;
    }

    void addWord(String word, Integer frequency) {
        wordFrequencies.put(word, frequency);
    }
}
