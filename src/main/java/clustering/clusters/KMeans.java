package clustering.clusters;

import clustering.common.Article;
import clustering.common.Pearson;

import java.util.ArrayList;
import java.util.List;

public class KMeans {
    private List<Centroid> centroids;
    private List<Article> articles;
    private Pearson pearson;

    public KMeans(List<Article> articles) {
        this.articles = articles;
        pearson = new Pearson();
    }

    public List<Centroid> getCentroids() {
        return centroids;
    }

    public void generate(int k) {
        centroids = new ArrayList<Centroid>();
        Randomizer rnd = new Randomizer(articles);
        for (int i = 0; i < k; i++) {
            centroids.add(rnd.createRandom());
        }

        boolean done = false;
        int cnt = 0;
        while (!done) {
            iterate();
            done = true;
            for (Centroid c : centroids) {
                c.recalcCenter();
                if (!c.matchesPreviousAssignment()) {
                    done = false;
                }
            }
            cnt++;
        }
        System.out.println("Iterations: " + cnt + ", Centroids: " + centroids.size() + "\n\n");
    }

    private void iterate() {
        resetCentroids();
        for (Article a : articles) {
            double bestScore = Double.MAX_VALUE;
            Centroid bestMatch = centroids.get(0);
            for (Centroid c : centroids) {
                double score = pearson.calculate(c.getArticle(), a);
                if (score < bestScore) {
                    bestMatch = c;
                    bestScore = score;
                }
            }
            bestMatch.addArticle(a);
        }
    }

    private void resetCentroids() {
        for (Centroid c : centroids) {
            c.saveLast();
        }
    }
}
