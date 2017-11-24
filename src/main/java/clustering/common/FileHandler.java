package clustering.common;

import clustering.wiki.WikiPage;

import java.io.*;
import java.util.*;

public class FileHandler {

    public List<Article> readFileData(String path) throws IOException {
        List<Article> articles = new ArrayList<Article>();
        File file = new File(path);
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();

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

    public void createWikiDataFile() throws IOException {
        File dataFile = new File("data/wikidata.txt");
        if (!dataFile.exists()) {
            dataFile.createNewFile();
            FileWriter fw = new FileWriter(dataFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            File dir = new File("data/wiki/");
            File[] subs = dir.listFiles();
            List<File> files = new ArrayList<>();
            List<WikiPage> articles = new ArrayList<WikiPage>();
            WordFrequencyMap allWords = new WordFrequencyMap();
            for (File s : subs) {
                Collections.addAll(files, s.listFiles());
            }

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
            for (String word : allWords.getWords()) {
                i++;
                bw.write(word + "\t");
            }
            System.out.println("Number of words after filtering: " + i);
            bw.newLine();
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
