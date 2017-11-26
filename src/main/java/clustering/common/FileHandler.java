package clustering.common;

import clustering.wiki.WikiPage;

import java.io.*;
import java.util.*;

/**
 * Class to handle file reading and writing
 * */
public class FileHandler {

    /**
     * Reads the data files based on path and creates articles with words from the data set.
     * @return articles - List of Article from the data file containing the words and word count
     * */
    public List<Article> readFileData(String path) throws IOException {
        List<Article> articles = new ArrayList<Article>();
        File file = new File(path);
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);

        String currentLine;

        String[] words = br.readLine().split("\t");
        while ((currentLine = br.readLine()) != null) {
            String[] data = currentLine.split("\t");
            Article a = new Article(data[0]);
            for (int i = 1; i < words.length; i++) {
                a.addWord(words[i], Integer.parseInt(data[i]));
            }
            articles.add(a);
        }
        br.close();
        reader.close();
        return articles;
    }

    /**
     * Creates the data file for wikipedia from the crawled data set
     * */
    public void createWikiDataFile() throws IOException {
        File dataFile = new File("data/wikidata.txt");
        //Only create if file doesn't exist. No need to write the file on every request
        // Should be broken out to separate function for better logic
        if (!dataFile.exists()) {
            dataFile.createNewFile();
            FileWriter fw = new FileWriter(dataFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            File dir = new File("data/wiki/");
            File[] subs = dir.listFiles();
            List<File> files = new ArrayList<File>();
            List<WikiPage> articles = new ArrayList<WikiPage>();
            WordFrequencyMap allWords = new WordFrequencyMap();

            //Loop all the subdirectories to get all the files
            for (File s : subs) {
                Collections.addAll(files, s.listFiles());
            }

            //Create WikiPage from all the files and count the word frequency
            for (File f : files) {
                WikiPage a = new WikiPage(extractArticleName(f.getName()));
                FileReader reader = new FileReader(f);
                BufferedReader br = new BufferedReader(reader);
                String currentLine;
                WordFrequencyMap wordCount = new WordFrequencyMap();

                while ((currentLine = br.readLine()) != null) {
                    String[] words = currentLine.split(" ");
                    for (int i = 1; i < words.length; i++) {
                        String word = words[i];
                        wordCount.addWord(word, 1);
                    }
                }

                for (String w : wordCount.getWords()) {
                    a.addWord(w, wordCount.get(w));
                    allWords.addWord(w, wordCount.get(w));
                }

                articles.add(a);
            }

            bw.write("Articles\t");
            System.out.println(allWords.getHighestWordCount());
            System.out.println(allWords.getWords().size());
            int i = 0;
            int minCount = 25;
            System.out.println("Number of words before filtering: " + allWords.getWords().size());
            allWords.filterWords(minCount);

            //Save the list of words at the tip of the document
            for (String word : allWords.getWords()) {
                i++;
                bw.write(word + "\t");
            }

            System.out.println("Number of words after filtering: " + i);
            bw.newLine();

            //Loops all the words and writes the word count for that word in each article
            for (WikiPage a : articles) {
                bw.write(a.getTitle() + "\t");

                for (String word : allWords.getWords()) {
                    bw.write(a.get(word) + "\t");
                }

                bw.newLine();
            }

            bw.close();
            System.out.println("Finished writing file.");
        }

    }

    private String extractArticleName(String filename) {
        String[] name = filename.split("\\.");
        return name[0].replaceAll("_+", " ");
    }

}
