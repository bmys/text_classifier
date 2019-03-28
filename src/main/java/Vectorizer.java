import java.util.*;

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


    public static Map sortByValue(Map<String, Integer> corpus)
    {

        List<Map.Entry<String, Integer> > list = new LinkedList<>(corpus.entrySet());


        list.sort(Comparator.comparing(o -> (o.getValue())));


        HashMap<String, Integer> temp = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> el : list) {
            temp.put(el.getKey(), el.getValue());
        }
        return temp;
    }

    public static List getStopWords(Map<String, Integer> corpus, int percent){

        int index = Math.round(corpus.size() - corpus.size() * percent/100.0f);

        List<Map.Entry<String, Integer> > list = new LinkedList<>(corpus.entrySet());

        return list.subList(index, list.size());
    }
}
