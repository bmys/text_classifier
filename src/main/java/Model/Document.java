package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Document {
    private HashMap<String, ArrayList> labels = new HashMap<>();
    private String rawText = "";
    private ArrayList<String> tokens = new ArrayList<>();

    public Document() {
    }

    public Document(HashMap<String, ArrayList> labels, String rawText, ArrayList<String> tokens) {
        this.labels = labels;
        this.rawText = rawText;
        this.tokens = tokens;
    }

    public HashMap<String, ArrayList> getLabels() {
        return labels;
    }

    public void setLabels(HashMap<String, ArrayList> labels) {
        this.labels = labels;
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    public ArrayList<String> getTokens() {
        return tokens;
    }

    public void setTokens(String[] tokens) {
        this.tokens.addAll(Arrays.asList(tokens));
    }

    public void setTokens(ArrayList<String> tokens) {
        this.tokens = tokens;
    }
}
