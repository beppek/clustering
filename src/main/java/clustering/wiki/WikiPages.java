package clustering.wiki;

import clustering.common.Article;
import clustering.common.FileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WikiPages {
    private List<Article> pages = new ArrayList<Article>();
    private FileHandler fileHandler = new FileHandler();

    void createWordFrequencyFile() throws IOException {
        fileHandler.createWikiDataFile();
    }

    /**
     * Reads the wikidata.txt file
     * */
    void readWikiData() throws IOException {
        pages = fileHandler.readFileData("data/wikidata.txt");
    }

    List<Article> getPages() {
        return pages;
    }
}
