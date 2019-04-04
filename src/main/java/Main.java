import model.Article;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        Document doc = DataLoader.loadSgml("/home/arch/IdeaProjects/ksr/resources/reut2-019.sgm");
        List<Article> articles = ArticleFactory.loadArticles(doc);
        System.out.println(articles.size());
        articles = DataLoader.filterArticlesByLocation(articles, new ArrayList<>(Arrays.asList("west-germany", "usa", "france", "uk", "canada", "japan")));
        System.out.println(articles.size());

        List<model.Document> documents = DocumentsFactory.documentsFromArticles(articles);

        for(model.Document dc: documents){
            System.out.println(dc.getTokens());
            System.out.println(dc.getLabels().get("locations"));
        }
    }
}
