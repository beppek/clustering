package clustering.clusters;

import clustering.common.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the K-Means ordering of clusters
 * */
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

    /**
     * Generates the K-Means clustering based on k.
     * Centroids are created from random articles in the list.
     * Iterates over the articles until the centroids are set and current assignment matches the previous one.
     * @param k - int representing the number of centroids to organize k-means clustering from
     * */
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

    /**
     * Iterates over the articles to find the best matching centroid for each article
     * */
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

    /**
     * Resets the centroids by saving the last assignment for each centroid for future comparison
     * */
    private void resetCentroids() {
        for (Centroid c : centroids) {
            c.saveLast();
        }
    }
}
