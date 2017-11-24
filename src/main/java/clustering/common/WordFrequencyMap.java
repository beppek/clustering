package clustering.common;

import java.util.*;

public class WordFrequencyMap {
    private Map<String, Integer> words = new HashMap<>();
    private Set<String> filteredWords;

    public WordFrequencyMap() {
        initFilteredWordsList();
    }

    public void addWord(String w, int count) {
        if (passFilter(w)) {
            if (hasWord(w)) {
                updateWordCount(w, count);
            } else {
                words.put(w, count);
            }
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

    private void initFilteredWordsList() {
        String[] fw = {"the", "for", "with", "that", "from", "was", "oclc", "five", "wned", "were", "these", "create",
                "are", "nor", "ibn", "isbn", "com", "hello", "once", "ones", "onto", "off", "iii", "und", "this",
                "can", "not", "have", "had", "has", "and", "also", "use", "one", "two", "wikipedia", "wikidata", "but",
                "it's", "its", "first", "some", "links", "time", "lesser", "'horse'", ""};
        filteredWords = new HashSet<String>(Arrays.asList(fw));
    }

    private void updateWordCount(String w, int count) {
        words.put(w, words.get(w) + count);
    }

    private boolean hasWord(String word) {
        return words.containsKey(word);
    }

    private boolean passFilter(String w) {
        return w.length() > 2 && !filteredWords.contains(w);
    }
}
