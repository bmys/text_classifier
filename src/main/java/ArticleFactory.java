import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticleFactory {
    public static List<Article> loadArticles(Document doc){
        List<Article> articles = new ArrayList<>();

        Elements reuters = doc.select("REUTERS");

        for(Element elem: reuters){
            String title = elem.select("TITLE").text();
            String text = elem.select("INNER").text();
            String places = elem.select("PLACES > D").text();
            List<String> placesList = new ArrayList<>(Arrays.asList(places.split("\\s+")));
            Article art = new Article(title, text, placesList);
            articles.add(art);
        }
        return articles;
    }
}
