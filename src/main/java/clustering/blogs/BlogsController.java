package clustering.blogs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import clustering.common.Article;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blogs")
public class BlogsController {

    @RequestMapping("/k-clusters")
    public List<Article> kClusters() {
        Blogs blogs = new Blogs();
        List<Article> items = new ArrayList<>();
        try {
            items = blogs.readBlogData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }
}