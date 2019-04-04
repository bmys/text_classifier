package model;
import java.util.*;

public class Document {
    private List<String> tokens;
    private Map<String, List<String>> labels;
    private Map<String, Double> feuters;

    public Document() {
        this.tokens = new ArrayList<>();
        this.labels = new HashMap<>();
        this.feuters = new HashMap<>();
    }

    public Document(List<String> tokens, Map<String, List<String>> labels) {
        this.tokens = tokens;
        this.labels = labels;
        this.feuters = new HashMap<>();

    }

    public List<String> getTokens() {
        return tokens;
    }

    public Map<String, List<String>> getLabels() {
        return labels;
    }

    public Map<String, Double> getFeuters() {
        return feuters;
    }

    public void removeTokens(List<String> tokensToRemove){
        this.tokens.removeAll(tokensToRemove);
    }
}
