package clustering.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public List<Article> readFileData(String path) throws IOException {
        List<Article> articles = new ArrayList<>();
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

}
