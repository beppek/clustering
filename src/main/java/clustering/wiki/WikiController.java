package clustering.wiki;

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
 * Java Spring Controller for wiki routes
 * */
@RestController
@CrossOrigin()
@RequestMapping("/wiki")
public class WikiController {

    /**
     * Gets K-Means clustering for the wikipedia data
     * */
    @RequestMapping("/kmeans")
    public String kClusters(@RequestParam(value="k", defaultValue = "4") String stringK) {
        int k = Integer.parseInt(stringK);
        WikiPages pages = new WikiPages();
        try {
            pages.createWordFrequencyFile();
            pages.readWikiData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        KMeans kMeans = new KMeans(pages.getPages());
        kMeans.generate(k);
        HTMLBuilder builder = new HTMLBuilder();
        String html = builder.buildKMeansHTML(kMeans.getCentroids());
        return html;
    }

    /**
     * Gets hierarchical clustering for the wikipedia data
     * */
    @RequestMapping("/hierarchy")
    public String hierarchy() {
        WikiPages pages = new WikiPages();
        try {
            pages.createWordFrequencyFile();
            pages.readWikiData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Hierarchy h = new Hierarchy(pages.getPages());
        h.generate();
        Cluster root = h.getClusters().get(0);
        TreeBuilder tb = new TreeBuilder(root);
        return tb.buildHTMLTree();
    }
}