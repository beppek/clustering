package clustering.common;

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
        return articles;
    }

    public void createWikiDataFile() throws IOException {
        File dataFile = new File("data/wikidata.txt");
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }
        FileWriter fw = new FileWriter(dataFile.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        File dir = new File("data/wiki/");
        File[] subs = dir.listFiles();
        List<File> files = new ArrayList<>();
        List<Article> articles = new ArrayList<Article>();
        WordFrequencyMap allWords = new WordFrequencyMap();
        for (File s : subs) {
            Collections.addAll(files, s.listFiles());
        }

        for (File f : files) {
            Article a = new Article(extractArticleName(f.getName()));
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
        bw.write("Articles ");
        System.out.println(allWords.getHighestWordCount());
        System.out.println(allWords.getWords().size());
        int i = 0;
        for (String word : allWords.getWords()) {
            if (allWords.get(word) > 150)
                i++;
                bw.write(word + "\t");
        }
        System.out.println("Words with more than 100 counts: " + i);
        bw.newLine();
        for (Article a : articles) {
            bw.write(a.getTitle() + "\t");
            for (String word : allWords.getWords()) {
//                bw.write();
            }
        }
        bw.close();
    }

    private String extractArticleName(String filename) {
        String[] name = filename.split("\\.");
        return name[0].replaceAll("_+", " ");
    }

}
