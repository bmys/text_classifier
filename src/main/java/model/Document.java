package model;
import java.util.*;

public class Document {
    private List<String> tokens;
    private Map<String, List<String>> labels;
    private Map<String, Double> features;

    public Document() {
        this.tokens = new ArrayList<>();
        this.labels = new HashMap<>();
        this.features = new HashMap<>();
    }

    public Document(List<String> tokens, Map<String, List<String>> labels) {
        this.tokens = tokens;
        this.labels = labels;
        this.features = new HashMap<>();

    }

    public List<String> getTokens() {
        return tokens;
    }
    public Map<String, List<String>> getLabels() {
        return labels;
    }

    public Map<String, Double> getFeatures() {
        return features;
    }

    public void removeTokens(List<String> tokensToRemove){
        this.tokens.removeAll(tokensToRemove);
    }
}
