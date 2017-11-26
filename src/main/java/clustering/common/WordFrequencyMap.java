package clustering.common;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class to handle word frequency with filtering based on common words
 * */
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

    public int get(String w) throws NullPointerException {
        return words.get(w);
    }

    /**
     * Eliminates the most common words as they would be found in all articles.
     * Also eliminates the least common words which might be junk words or words that have little bearing on the classification of the data since they are not used enough
     * @param minCount - integer representing the minimum number of times a word should appear. Lower counts get eliminated from the data set.
     * */
    public void filterWords(int minCount) {
        int max = (int) (words.size() * 0.7);
        int min = (int) (words.size() * 0.4);
        System.out.println("Getting sublist between indexes: " + max + " and " + min);
        Map<String, Integer> sorted = words.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getValue)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        List<String> keys = new ArrayList<>(sorted.keySet()).subList(min, max);
        List<Integer> values = new ArrayList<>(sorted.values()).subList(min, max);
        words = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            words.put(keys.get(i), values.get(i));
        }
        System.out.println("Wordcount for least frequent word: " + values.get(values.size() - 1));
        Map<String, Integer>  wordsCopy = new HashMap<>(words);
        Iterator it = wordsCopy.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if ((int)pair.getValue() < minCount) {
                String word = (String) pair.getKey();
                words.remove(word);
            }
        }
    }

    private void initFilteredWordsList() {
        String[] fw = {
                "the", "for", "with", "that", "from", "was", "oclc", "five", "wned", "were", "these", "create", "of", "in",
                "are", "nor", "ibn", "isbn", "com", "hello", "once", "ones", "onto", "off", "iii", "und", "this",
                "can", "not", "have", "had", "has", "and", "also", "use", "one", "two", "wikipedia", "wikidata", "but",
                "it's", "its", "first", "some", "links", "time", "lesser", "'horse'", "see", "who", "john", "you", "there", "their",
                "peter", "both", "does", "did", "then", "them", "all", "any", "non", "now", "org", "out", "per", "less", "where", "an",
                "used", "uses", "just", "back", "such", "day", "became", "few", "than", "they", "here", "will", "when", "what", "her", "his",
                "how", "made", "man", "men", "woman", "women", "new", "own", "though", "same"
        };
        filteredWords = new HashSet<String>(Arrays.asList(fw));
    }

    private void updateWordCount(String w, int count) {
        words.put(w, words.get(w) + count);
    }

    private boolean hasWord(String word) {
        return words.containsKey(word);
    }

    /**
     * Run a word through the filter to see if it is contained in the list of junk words
     * */
    private boolean passFilter(String w) {
        return w.length() > 2 && !filteredWords.contains(w);
    }
}
