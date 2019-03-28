import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticleFactory {
    public static List<Article> loadArticles(Document doc){
        List<Article> articles = new ArrayList<Article>();

        Elements reuters = doc.select("REUTERS");

        for(Element elem: reuters){
            String title = elem.select("TITLE").text();


            String text = elem.select("INNER").text();
//            Document bodyDoc = Jsoup.parseBodyFragment(text);
//            Element body = bodyDoc.selectFirst("BODY");
//            text = body.text();




            String places = elem.select("PLACES > D").text();

//            String[] array = values.split("\\|", -1);

            List<String> placesList = new ArrayList<String>(Arrays.asList(places.split("\\s+")));
            Article art = new Article(title, text, placesList);
            articles.add(art);
        }

        return articles;

    }


}
