import metrics.iMetric;
import model.Corpus;

public class knn {
    private int k;
    private iMetric metric;
    private model.Corpus corpus;

    public knn(int k, iMetric metric, Corpus corpus) {
        this.k = k;
        this.metric = metric;
        this.corpus = corpus;
    }
}
