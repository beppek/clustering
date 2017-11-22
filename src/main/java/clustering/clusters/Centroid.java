package clustering.clusters;

import clustering.common.Article;

import java.util.List;

class Centroid {

    private Article article;
    private List<Article> cluster;

    Centroid(Article a) {
        article = a;
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
        return false;
    }
}
