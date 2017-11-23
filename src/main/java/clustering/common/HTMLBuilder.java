package clustering.common;

import clustering.clusters.Centroid;

import java.util.List;

public class HTMLBuilder {
    public String buildKMeansHTML(List<Centroid> centroids) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Centroid c : centroids) {
            sb.append("<h4>Centroid nr " + i++ + " randomly generated from " + c.getArticle().getTitle() + "</h4>");
            sb.append("\t<ul>");
            for (Article a : c.getCluster()) {
                sb.append("\t\t<li>Title: " + a.getTitle() + "</li>");
            }
            sb.append("</ul>");
        }
        return sb.toString();
    }
}
