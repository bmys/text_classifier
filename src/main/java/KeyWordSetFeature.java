import java.util.List;
import java.util.Map;

public class KeyWordSetFeature implements iFeatureExtractor {
    private Map<String, Float> keywords;
    private String label;
    private double totalMemberShip;

    public KeyWordSetFeature(Map<String, Float> keywords, String label) {
        this.keywords = keywords;
        this.label = "KeyWordSet_" + label;

        for(Map.Entry<String, Float> val: keywords.entrySet()){
            this.totalMemberShip += val.getValue();
        }
    }

    public String getLabel() { return label; }

    @Override
    public Map.Entry<String, Double> getFeatureValue(List<String> text) {
        double acc = 0;

        for(String word: text){
            if(keywords.containsKey(word)){
                acc += this.keywords.get(word);
            }
        }
        return new Entry<>(label, acc / totalMemberShip);
    }
}
