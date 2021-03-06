package clustering.blogs;

import java.io.IOException;

import clustering.clusters.Cluster;
import clustering.clusters.Hierarchy;
import clustering.clusters.KMeans;
import clustering.common.HTMLBuilder;
import clustering.common.TreeBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Java Spring Controller for blogs routes
 * */
@RestController
@CrossOrigin()
@RequestMapping("/blogs")
public class BlogsController {

    /**
     * Returns a HTML string representation of the k-means clustering of the blog data
     * */
    @RequestMapping("/kmeans")
    public String kClusters(@RequestParam(value="k", defaultValue = "4") String stringK) {
        int k = Integer.parseInt(stringK);
        Blogs blogs = new Blogs();
        try {
            blogs.readBlogData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        KMeans kMeans = new KMeans(blogs.getBlogs());
        kMeans.generate(k);
        HTMLBuilder builder = new HTMLBuilder();
        return builder.buildKMeansHTML(kMeans.getCentroids());
    }

    /**
     * Returns a HTML string tree representation of hierarchical clustering of the data
     * */
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
        return tb.buildHTMLTree();
    }
}