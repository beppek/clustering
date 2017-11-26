package clustering.wiki;

import clustering.common.Article;
import clustering.common.WordFrequencyMap;

/**
 * Class representing a Wikipedia page with the words in the article and the frequency of those words
 * */
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
