import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

//        Document doc = DataLoader.loadSgml("/home/arch/IdeaProjects/ksr/resources/reut2-017.sgm");
//
//        List<Article> k = ArticleFactory.loadArticles(doc);
//
//        System.out.println(k.size());
//
//        k = DataLoader.filterArticles(k);
//
//        System.out.println("_____");
//
//        System.out.println(k.size());
//
//
//        for(Article art: k){
//            System.out.println(art);
//        }
//




        String e = Preprocessing.removeNonLetterChars(" (Astoria Fibra-Steel, Inc) for cash. 135 billion lire in 1987 from 113 billion");
        e = e.toLowerCase();

        String c = Preprocessing.removeNonLetterChars(" Moreover you can do this in one single line if you are ok in using regular expression.");
        c = c.toLowerCase();

        List<String> vec1 = Vectorizer.string2vec(e);
        List<String> vec2 = Vectorizer.string2vec(c);

        List<List<String>> cor = new ArrayList<>();
        cor.add(vec1);
        cor.add(vec2);

        Map<String, Integer> o = Vectorizer.generateCorpus(cor);

        System.out.println(o);
        Map k = Vectorizer.sortByValue(o);

        List f = Vectorizer.getStopWords(k, 25);

//        for(Object el : f){
//            k.remove(el);
//        }

        k = Vectorizer.getWordFeatureVector(k, f);

        System.out.println(k);




//        System.out.println(Vectorizer.sortByValue(o));


//        System.out.println(Arrays.asList(e.split("\\s+")));
//        System.out.println(" (Astoria Fibra-Steel, Inc) for cash. 135 billion lire in 1987 from 113 billion".trim());



//  FIRST MISSISSIPPI CORP <FRM> SETS QUARTERLY
//        List<Article> articles = ArticleFactory.loadArticles(doc);
//
//        System.out.println(articles.get(2).toString());


//        HashMap<String, Integer> corpus = new HashMap<>();
//
//        corpus.put("Ama", 5);
//        if(corpus.containsKey("Ama")){
//            System.out.println("Jest");
//        }
//        else{
//            System.out.println("Nie ma");
//        }


    }

}
