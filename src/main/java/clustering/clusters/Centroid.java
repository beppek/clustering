package clustering.clusters;

import clustering.common.Article;

import java.util.HashSet;
import java.util.Set;

/**
 * Class representing a Centroid in the K-Means clustering
 * */
public class Centroid {

    private Article article;
    private Set<Article> prevCluster;
    private Set<Article> cluster;

    /**
     * @param a - Article to be the basis for the centroid
     * */
    Centroid(Article a) {
        article = a;
        cluster = new HashSet<Article>();
    }

    /**
     * @return article - Article the centroid was based upon
     * */
    public Article getArticle() {
        return article;
    }

    /**
     * @return cluster - Set of articles in the centroid cluster
     * */
    public Set<Article> getCluster() {
        return cluster;
    }

    /**
     * Saves the last iteration of clusters for future comparison
     * */
    void saveLast() {
        prevCluster = cluster;
        cluster = new HashSet<Article>();
    }

    /**
     * Add an article to the cluster
     * */
    void addArticle(Article a) {
        cluster.add(a);
    }

    /**
     * Recalculates the center of the centroid
     * */
    void recalcCenter() {
        for (int i = 0; i < article.getWords().size(); i++) {
            double avg = 0.0;
            for (Article a : cluster) {
                avg += a.getWords().get(i).getCount();
            }
            avg /= (double)cluster.size();
            article.getWords().get(i).setCount(avg);
        }
    }

    /**
     * Checks if previous and current assignments of the centroid cluster are the same
     * */
    boolean matchesPreviousAssignment() {
        return prevCluster.containsAll(cluster);
    }
}
