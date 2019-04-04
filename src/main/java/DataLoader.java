import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataLoader {

    static Document loadSgml(String path) throws IOException {

        File input = new File(path);
        Scanner s = new Scanner(input).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        result = result.replace("<BODY", "<INNER");
        return Jsoup.parse(result);
    }

    public static List<Article> loadFromDir(String path) throws IOException {
        List<Article> articles = new ArrayList<>();

        File dir = new File(path);

        File [] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".sgm");
            }
        });

        assert files != null;

        Arrays.sort(files);

        for (File file : files) {
            Document doc = loadSgml(file.toString());
            List<Article> articlesFromDoc = ArticleFactory.loadArticles(doc);
            System.out.println(articlesFromDoc.size());
            articles.addAll(articlesFromDoc);
        }
        return articles;
    }

    public static List<Article> filterArticles(List<Article> articles){
        List<String> locations = new ArrayList<>(Arrays.asList("west-germany", "usa", "france", "uk", "canada", "japan"));
        List<Article> filteredArticles = new ArrayList<Article>();

        for(Article art: articles){
            List loc = art.getLocations();

            if(loc.size() == 1 && locations.contains(loc.get(0))){
                filteredArticles.add(art);
            }
        }
        return filteredArticles;
    }
}
