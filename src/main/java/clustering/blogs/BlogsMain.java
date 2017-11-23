package clustering.blogs;

import clustering.clusters.Centroid;
import clustering.clusters.Cluster;
import clustering.clusters.Hierarchy;
import clustering.clusters.KMeans;
import clustering.common.Article;
import clustering.common.TreeBuilder;

import java.io.IOException;
import java.util.List;
import javax.swing.JTree;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class BlogsMain {
    public static void main(String[] args) {
        Blogs blogs = new Blogs();
        try {
            blogs.readBlogData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        kmeans(blogs);
//        hierarchy(blogs);
    }

    private static void kmeans(Blogs blogs) {
        KMeans kMeans = new KMeans(blogs.getBlogs());
        kMeans.generate(4);
        List<Centroid> centroids = kMeans.getCentroids();
        int i = 0;
        for (Centroid c : centroids) {
            System.out.println("Centroid nr: " + i++ + " randomly created from " + c.getArticle().getTitle());
            for (Article a : c.getCluster()) {
                System.out.println(a.getTitle());
            }
        }
    }

    private static void hierarchy(Blogs blogs) {
        Hierarchy h = new Hierarchy(blogs.getBlogs());
        h.generate();
        Cluster root = h.getClusters().get(0);
        TreeBuilder tb = new TreeBuilder(root);
        JTree tree = tb.buildJTree();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane scrollPane = new JScrollPane(tree);
        frame.add(scrollPane);

        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
