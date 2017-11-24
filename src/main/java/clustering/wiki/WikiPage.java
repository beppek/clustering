package clustering.wiki;

import clustering.common.Article;
import clustering.common.WordFrequencyMap;


public class WikiPage extends Article {
    private WordFrequencyMap words;

    public WikiPage(String title) {
        super(title);
        words = new WordFrequencyMap();
    }

    public void addWord(String word, int count) {
        words.addWord(word, count);
    }

    public int get(String word) {
        int count;
        try {
            count = words.get(word);
        } catch (NullPointerException e) {
            return 0;
        }
        return count;
    }
}
