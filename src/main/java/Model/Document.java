package Model;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Document implements Cloneable {

  private HashMap<String, ArrayList<String>> labels = new HashMap<>();
  private String rawText = "";
  private ArrayList<String> tokens = new ArrayList<>();
  private Map<String, Double> numericFeatures = new HashMap<>();
  private Map<String, String> stringFeatures = new HashMap<>();

  public Document() {
  }

  public Document(HashMap<String, ArrayList<String>> labels, String rawText,
      ArrayList<String> tokens) {
    this.labels = labels;
    this.rawText = rawText;
    this.tokens = tokens;
  }
  // TODO: clone method
//  public Object clone() throws
//
//  }

  public HashMap<String, ArrayList<String>> getLabels() {
    return labels;
  }

  public void setLabels(HashMap<String, ArrayList<String>> labels) {
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

  public void setTokens(List<String> tokens) {
    this.tokens = new ArrayList<>(tokens);
  }

  void setNumericFeature(Entry<String, Double> feature) {
    numericFeatures.put(feature.getKey(), feature.getValue());
  }

  void setStringFeature(Entry<String, String> feature) {
    stringFeatures.put(feature.getKey(), feature.getValue());
  }

  public ImmutableMap<String, Double> getNumericFeatures() {
    return ImmutableMap.copyOf(numericFeatures);
  }

  public ImmutableMap<String, String> getStringFeatures() {
    return ImmutableMap.copyOf(stringFeatures);
  }

  public void setNumericFeatures(Map<String, Double> numericFeatures) {
    this.numericFeatures = numericFeatures;
  }

  public void setStringFeatures(Map<String, String> stringFeatures) {
    this.stringFeatures = stringFeatures;
  }
}
