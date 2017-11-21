package clustering.blogs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Blogs {
    List<Blog> blogs = new ArrayList<>();

    /**
     * Reads the blogdata.txt file
     * */
    public List<Blog> readBlogData() throws IOException {
        File file = new File("data/blogdata.txt");
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();

        String currentLine;

        String[] words = br.readLine().split("\t");
        while ((currentLine = br.readLine()) != null) {
            String[] data = currentLine.split("\t");
            Blog blog = new Blog(data[0]);
            blogs.add(blog);
//            for (int i = 0; i < currentLine.length(); i++) {
//                char c = currentLine.charAt(i);
//                if (c == '\t') {
//                    items.add(sb.toString());
//                    sb.setLength(0);
//                } else {
//                    sb.append(c);
//                }
//            }
        }
        return blogs;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }
}
