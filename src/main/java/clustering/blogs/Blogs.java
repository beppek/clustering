package clustering.blogs;

import clustering.common.Article;
import clustering.common.FileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents blogs read from a file of data
 * */
class Blogs {
    private List<Article> blogs = new ArrayList<Article>();

    /**
     * Reads the blogdata.txt file
     * */
    void readBlogData() throws IOException {
        FileHandler fileHandler = new FileHandler();
        blogs = fileHandler.readFileData("data/blogdata.txt");
    }

    /**
     * @return List of blogs read from file
     * */
    List<Article> getBlogs() {
        return blogs;
    }
}
