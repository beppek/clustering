package clustering.wiki;

import clustering.clusters.Centroid;
import clustering.clusters.KMeans;
import clustering.common.Article;
import clustering.common.HTMLBuilder;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

public class WikiMain {
    public static void main(String[] args) {
        WikiPages pages = new WikiPages();
        try {
            pages.createWordFrequencyFile();
//            pages.readWikiData();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        kmeans(pages);
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
}
