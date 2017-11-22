package clustering.common;

public class Word {
    private String word;
    private double count;

    public Word(String word, double count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double c) {
        this.count = c;
    }
}
