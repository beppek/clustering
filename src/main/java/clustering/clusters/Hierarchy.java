package clustering.clusters;

import clustering.common.Article;
import clustering.common.Pearson;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Hierarchical clustering of data
 * */
public class Hierarchy {
    private List<Cluster> clusters;
    private List<Article> articles;
    private Pearson pearson;

    public Hierarchy(List<Article> a) {
        articles = a;
        pearson = new Pearson();
        init();
    }

    public List<Cluster> getClusters() {
        return clusters;
    }

    public Cluster getRoot() {
        return clusters.get(0);
    }

    /**
     * Initialize all articles as single clusters
     * */
    private void init() {
        clusters = new ArrayList<Cluster>();
        for (Article a : articles) {
            clusters.add(new Cluster(a));
        }
    }

    /**
     * Start merging clusters until there is only one left
     * */
    public void generate() {
        while (clusters.size() > 1) {
            iterate();
        }
    }

    /**
     * Iterate over the clusters to merge similar clusters
     * */
    private void iterate() {
        double closest = Double.MAX_VALUE;
        Cluster bestA = null;
        Cluster bestB = null;

        for (int i = 0; i < clusters.size(); i++) {
            for (int j = i + 1; j < clusters.size(); j++) {
                Cluster cA = clusters.get(i);
                Cluster cB = clusters.get(j);
                double distance = pearson.calculate(cA.getArticle(), cB.getArticle());
                if (distance < closest) {
                    closest = distance;
                    bestA = cA;
                    bestB = cB;
                }
            }
        }

        Cluster m = bestA.merge(bestB, closest);
        clusters.add(m);
        clusters.remove(bestA);
        clusters.remove(bestB);
    }
}
