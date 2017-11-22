package clustering.blogs;

import java.util.ArrayList;
import java.util.List;

public class Article {
    private String title;
    private List<Word> words = new ArrayList<>();

    public Article(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<Word> getWords() {
        return words;
    }

    public void addWord(String word, double count) {
        words.add(new Word(word, count));
    }
}
