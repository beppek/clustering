package clustering.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordFrequencyMap {
    private Map<String, Integer> words = new HashMap<>();

    public void addWord(String w, int count) {
        if (hasWord(w)) {
            updateWordCount(w, count);
        } else {
            words.put(w, count);
        }
    }

    public Integer getHighestWordCount() {
        return Collections.max(words.values());
    }

    public Set<String> getWords() {
        return words.keySet();
    }

    public int get(String w) {
        return words.get(w);
    }

    private void updateWordCount(String w, int count) {
        words.put(w, words.get(w) + count);
    }

    private boolean hasWord(String word) {
        return words.containsKey(word);
    }
}
