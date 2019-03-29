import java.util.*;

public class FeatureExtractor {

    static Map binaryExtractor(List <String> text, List <String> model){
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for(String word: model){
            if(text.contains(word)){
                temp.put(word, 1);
            }
            else{
                temp.put(word, 0);
            }
        }
        return temp;
    }

    static Map countExtractor(List <String> text, List <String> model) {

        HashMap<String, Integer> temp = new LinkedHashMap<>();

        for(String word : model) {
            int count = Collections.frequency(text, word);
            temp.put(word, count);
        }

        return temp;
    }
}


