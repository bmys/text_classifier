import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import ca.rmen.porterstemmer.PorterStemmer;


public class Main {
    public static void main(String[] args) throws IOException {
        Document doc = DataLoader.loadSgml("/home/arch/IdeaProjects/ksr/resources/reut2-019.sgm");
        List<Article> articles = ArticleFactory.loadArticles(doc);
        System.out.println(articles.size());
        articles = DataLoader.filterArticles(articles);
        System.out.println(articles.size());

        List<model.Document> documents = DocumentsFactory.documentsFromArticles(articles);
        System.out.println(documents.get(0).getTokens());
        PorterStemmer ps = new PorterStemmer();

        System.out.println(ps.stemWord("fastest"));
    }
}
