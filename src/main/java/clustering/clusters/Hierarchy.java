package clustering.clusters;

import clustering.blogs.Article;
import clustering.blogs.Pearson;

import java.util.ArrayList;
import java.util.List;

public class Hierarchy {
    private List<Cluster> clusters;
    private List<Article> articles;
    private Pearson pearson = new Pearson();

    public Hierarchy(List<Article> a) {
        articles = a;
    }

    public List<Cluster> getClusters() {
        return clusters;
    }

    private void init() {
        clusters = new ArrayList<>();
        for (Article a : articles) {
            clusters.add(new Cluster(a));
        }
    }

    public void iterate() {
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
