package clustering.blogs;

import clustering.common.Article;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Blogs {
    private List<Article> blogs = new ArrayList<Article>();

    /**
     * Reads the blogdata.txt file
     * */
    void readBlogData() throws IOException {
        File file = new File("data/blogdata.txt");
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();

        String currentLine;

        String[] words = br.readLine().split("\t");
        while ((currentLine = br.readLine()) != null) {
            String[] data = currentLine.split("\t");
            Blog blog = new Blog(data[0]);
            for (int i = 1; i < words.length; i++) {
                blog.addWord(words[i], Integer.parseInt(data[i]));
            }
            blogs.add(blog);
        }
    }

    List<Article> getBlogs() {
        return blogs;
    }
}
