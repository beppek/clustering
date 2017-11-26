package clustering.clusters;

import clustering.common.Article;
import clustering.common.Word;

/**
 * Class to calculate Pearson similarity of two articles
 * */
class Pearson {
    /**
     * @return double representing the score of the comparison between two articles. 1.0 is a perfect score
     * */
    double calculate(Article a, Article b) {
        double sumA = 0;
        double sumAsq = 0;
        for (Word w : a.getWords()) {
            sumA += w.getCount();
            sumAsq += Math.pow(w.getCount(), 2);
        }
        double sumB = 0;
        double sumBsq = 0;
        for (Word w : b.getWords()) {
            sumB += w.getCount();
            sumBsq += Math.pow(w.getCount(), 2);
        }

        double pSum = 0;
        int n = Math.min(a.getWords().size(), b.getWords().size());
        for (int i = 0; i < n; i++) {
            pSum += a.getWords().get(i).getCount() * b.getWords().get(i).getCount();
        }

        double num = pSum - (sumA * sumB / n);
        double den = Math.sqrt((sumAsq - Math.pow(sumA, 2) / n) * (sumBsq - Math.pow(sumB, 2) / n));

        if (den == 0) {
            return 0;
        }

        return 1.0 - num / den;
    }
}
