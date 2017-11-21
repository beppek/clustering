package clustering.clusters;

import clustering.blogs.*;

public class Cluster {
    String vec;
    Cluster parent;
    Cluster left;
    Cluster right;
    double distance;
    Article article;
    String id;

    Cluster() {

    }

    Cluster(Article a) {
        setArticle(a);
    }

    Cluster(String vec, Cluster left, Cluster right, double distance, String id) {

    }

    void setVec(String vec) {
        this.vec = vec;
    }

    String getVec() {
        return this.vec;
    }

    void setParent(Cluster p) {
        this.parent = p;
    }

    void setLeft(Cluster left) {
        this.left = left;
    }

    void setRight(Cluster right) {
        this.right = right;
    }

    void setDistance(double distance) {
        this.distance = distance;
    }

    Article getArticle() {
        return this.article;
    }

    void setArticle(Article a) {
        this.article = a;
    }

    void setId(String id) {
        this.id = id;
    }

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
}
