import java.util.Collections;
import java.util.List;
import java.util.Map;

public class KeyWordDenseFeature implements iFeatureExtractor{
    private List<String> keywords;

    public KeyWordDenseFeature(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public Map.Entry<String, Double> getFeatureValue(List<String> text) {
        double counter =0;
        for(String keyword: keywords){
            counter += Collections.frequency(this.keywords, keyword);
        }
        return new Entry<String, Double>("keyword_counter", Math.log(counter / text.size()));
    }
}
