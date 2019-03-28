import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Vectorizer {

    public static List<String> string2vec(String text){
        return Arrays.asList(text.split("\\s+"));
    }

    public static HashMap<String, Integer> generateCorpus( List<List<String>> documents){
        HashMap<String, Integer> corpus = new HashMap<>();

        for(List<String> doc: documents){
            for(String word: doc){

                if(corpus.containsKey(word)){
                    corpus.put(word, corpus.get(word) + 1);
                }
                else{
                    corpus.put(word, 1);
                }

            }
        }

        return corpus;
    }
}
