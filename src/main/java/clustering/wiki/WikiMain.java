package clustering.wiki;

import clustering.clusters.Centroid;
import clustering.clusters.Cluster;
import clustering.clusters.Hierarchy;
import clustering.clusters.KMeans;
import clustering.common.Article;
import clustering.common.TreeBuilder;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class WikiMain {
    public static void main(String[] args) {
        WikiPages pages = new WikiPages();
        try {
            pages.createWordFrequencyFile();
            pages.readWikiData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        kmeans(pages);
        hierarchy(pages);
    }

    private static void kmeans(WikiPages pages) {
        int k = 4;
        KMeans kMeans = new KMeans(pages.getPages());
        kMeans.generate(k);
        List<Centroid> centroids = kMeans.getCentroids();
        int i = 1;
        for (Centroid c : centroids) {
            System.out.println();
            System.out.println("Centroid nr: " + i++ + " randomly created from " + c.getArticle().getTitle());
            System.out.println();
            for (Article a : c.getCluster()) {
                System.out.println(a.getTitle());
            }
        }
    }

    private static void hierarchy(WikiPages pages) {
        Hierarchy h = new Hierarchy(pages.getPages());
        h.generate();
        Cluster root = h.getRoot();
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
