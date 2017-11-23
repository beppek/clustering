package clustering.clusters;

import clustering.common.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Randomizer {
    private List<Article> articles;

    Randomizer(List<Article> a) {
        articles = new ArrayList<>(a);
    }

    Centroid createRandom() {
        Random r = new Random();
        Article randomArt = articles.get(r.nextInt(articles.size()));
        articles.remove(randomArt);
        return new Centroid(randomArt);
    }
}
