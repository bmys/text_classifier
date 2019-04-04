import model.Article;
import model.Corpus;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
//        Document doc = DataLoader.loadSgml("/home/arch/IdeaProjects/ksr/resources/reut2-019.sgm");
        List<Article> articles =DataLoader.loadFromDir("/home/arch/IdeaProjects/ksr/resources/");
//        List<Article> articles = ArticleFactory.loadArticles(doc);
        System.out.println(articles.size());
        articles = DataLoader.filterArticlesByLocation(articles, new ArrayList<>(Arrays.asList("west-germany", "usa", "france", "uk", "canada", "japan")));
        System.out.println(articles.size());

        List<model.Document> documents = DocumentsFactory.documentsFromArticles(articles);
        Corpus corpus = new Corpus();

        for(model.Document dc: documents){
            corpus.addDocument(dc);
        }


//        for(Map.Entry<String, Integer> entry: corpus.getWordCounter().entrySet()){
//            System.out.println(entry);
//        }

//        Vectorizer.sortByValue(corpus.getWordCounter());
        Map<String, Integer> k = Vectorizer.sortByValue(corpus.getWordCounter());
        System.out.println(k);
        List<String> newStopWords = Vectorizer.getMostCommonWords(k , 0.5f);



    }
}
