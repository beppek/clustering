package clustering.blogs;

import clustering.clusters.Cluster;
import clustering.clusters.Hierarchy;
import clustering.common.TreeBuilder;

import java.io.IOException;
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
