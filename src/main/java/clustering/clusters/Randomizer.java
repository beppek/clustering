package clustering.clusters;

import clustering.common.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class to randomly pick articles and create centroids from them
 * */
class Randomizer {
    private List<Article> articles;

    Randomizer(List<Article> a) {
        articles = new ArrayList<Article>(a);
    }

    /**
     * Creates a random centroid from the list of available articles and removes it from list to ensure the centroids in the cluster are different
     * */
    Centroid createRandom() {
        Random r = new Random();
        Article randomArt = articles.get(r.nextInt(articles.size()));
        articles.remove(randomArt);
        return new Centroid(randomArt);
    }
}
