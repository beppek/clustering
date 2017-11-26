package clustering.clusters;

import clustering.common.Article;
import clustering.common.Word;

/**
 * Class representing a cluster of Articles
 * */
public class Cluster {
    private Cluster parent;
    private Cluster left = null;
    private Cluster right = null;
    private double distance;
    private Article article;

    Cluster() {

    }

    Cluster(Article a) {
        setArticle(a);
    }

    void setParent(Cluster p) {
        this.parent = p;
    }

    public Cluster getLeft() {
        return left;
    }

    public boolean hasLeft() {
        return left != null;
    }

    void setLeft(Cluster left) {
        this.left = left;
    }

    public Cluster getRight() {
        return right;
    }

    public boolean hasRight() {
        return right != null;
    }

    void setRight(Cluster right) {
        this.right = right;
    }

    void setDistance(double distance) {
        this.distance = distance;
    }

    public Article getArticle() {
        return this.article;
    }

    public double getDistance() {
        return distance;
    }

    void setArticle(Article a) {
        this.article = a;
    }

    /**
     * Merges 2 clusters
     * Distance is set on parent to show how close the merged clusters are
     * */
    Cluster merge(Cluster oc, double distance) {
        Cluster p = new Cluster();
        p.setLeft(this);
        this.parent = p;
        p.setRight(oc);
        oc.setParent(p);

        Article nA = new Article("");
        for (int i = 0; i < article.getWords().size(); i++) {
            Word wA = article.getWords().get(i);
            Word wB = oc.article.getWords().get(i);
            double nCnt = (wA.getCount() + wB.getCount()) / 2.0;
            nA.addWord(wA.getWord(), nCnt);
        }

        p.setArticle(nA);
        p.setDistance(distance);

        return p;
    }

    @Override
    public String toString() {
        return article.getTitle();
    }
}
