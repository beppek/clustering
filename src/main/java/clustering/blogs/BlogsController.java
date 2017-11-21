package clustering.blogs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blogs")
public class BlogsController {

//    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/k-clusters")
    public List<Blog> kClusters() {
        Blogs blogs = new Blogs();
        List<Blog> items = new ArrayList<>();
        try {
            items = blogs.readBlogData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }
}