package clustering.blogs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import clustering.clusters.Cluster;
import clustering.clusters.Hierarchy;
import clustering.clusters.KMeans;
import clustering.common.Article;
import clustering.common.TreeBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blogs")
public class BlogsController {

    @RequestMapping("/kmeans")
    public String kClusters() {
        Blogs blogs = new Blogs();
        try {
            blogs.readBlogData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        KMeans kMeans = new KMeans(blogs.getBlogs());
        kMeans.generate(4);
        return "Not yet";
    }

    @RequestMapping("/hierarchy")
    public String hierarchy() {
        Blogs blogs = new Blogs();
        try {
            blogs.readBlogData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Hierarchy h = new Hierarchy(blogs.getBlogs());
        h.generate();
        Cluster root = h.getClusters().get(0);
        TreeBuilder tb = new TreeBuilder(root);
        String htmlTree = tb.buildHTMLTree();
        return htmlTree;
    }
}