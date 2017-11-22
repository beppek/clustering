package clustering.clusters;

import clustering.common.Article;

import java.util.ArrayList;
import java.util.List;

public class KMeans {
    List<Centroid> centroids;
    List<Article> articles;

    public KMeans(List<Article> articles) {
        this.articles = articles;
    }

    public void generate(int k) {
        centroids = new ArrayList<>();
        Randomizer rnd = new Randomizer(articles);
        for (int i = 0; i < k; i++) {
            centroids.add(rnd.createRandom());
        }

        boolean done = false;
        int cnt = 0;
        while (!done) {
            iterate();
            for (Centroid c : centroids) {
                c.recalcCenter();
            }

            done = true;
            for (Centroid c : centroids) {
                if (!c.matchesPreviousAssignment()) {
                    done = false;
                }
            }
            cnt++;
        }
    }

    private void iterate() {

    }
}
