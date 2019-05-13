package dataLoading;

import model.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

        File [] files = dir.listFiles((dir1, name) -> name.endsWith(".sgm"));

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

    public static List<Article> filterLocation(List<Article> articles, List<String> locations){
        List<Article> filteredArticles = new ArrayList<>();

        for(Article art: articles){
            List loc = art.getLocations();

            if(loc.size() == 1 && locations.contains(loc.get(0))){
                filteredArticles.add(art);
            }
        }
        return filteredArticles;
    }

//    public static List<Article> filterLocation(List<Article> articles, List<String> locations){
//        return articles.stream()
//                .filter(art -> art.getLocations().size() == 1)
//                .filter(art -> art.getLocations().stream().allMatch(loc -> locations.contains(loc)))
//                .collect(Collectors.toList());
//    }
}
