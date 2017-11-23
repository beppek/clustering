package clustering.clusters;

import clustering.common.Article;

import java.util.HashSet;
import java.util.Set;

public class Centroid {

    private Article article;
    private Set<Article> prevCluster;
    private Set<Article> cluster;

    Centroid(Article a) {
        article = a;
        cluster = new HashSet<Article>();
    }

    public Article getArticle() {
        return article;
    }

    public Set<Article> getCluster() {
        return cluster;
    }

    void saveLast() {
        prevCluster = cluster;
        cluster = new HashSet<Article>();
    }

    void addArticle(Article a) {
        cluster.add(a);
    }

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

    boolean matchesPreviousAssignment() {
        return prevCluster.containsAll(cluster);
    }
}
