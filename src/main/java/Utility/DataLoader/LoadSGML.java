package Utility.DataLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LoadSGML {

  static Document loadSgml(String path) throws IOException {

    File input = new File(path);
    Scanner s = new Scanner(input).useDelimiter("\\A");
    String result = s.hasNext() ? s.next() : "";
    result = result.replace("<BODY", "<INNER");
    return Jsoup.parse(result);
  }

  public static List<Model.Document> loadFromDir(String path, List<String> locs)
      throws IOException {
    List<Model.Document> articles = new ArrayList<>();

    File dir = new File(path);

    File[] files = dir.listFiles((dir1, name) -> name.endsWith(".sgm"));

    assert files != null;

    Arrays.sort(files);

    for (File file : files) {
      Document doc = loadSgml(file.toString());
      List<Model.Document> articlesFromDoc = loadArticlesWithLocation(doc, locs);
      System.out.println(articlesFromDoc.size());
      articles.addAll(articlesFromDoc);
    }
    return articles;
  }

  public static List<Model.Document> filterLocation(
      List<Model.Document> articles, List<String> locations) {
    List<Model.Document> filteredArticles = new ArrayList<>();

    for (Model.Document art : articles) {
      List<String> loc = art.getLabels().get("locations");

      if (loc.size() == 1 && locations.contains(loc.get(0))) {
        filteredArticles.add(art);
      }
    }
    return filteredArticles;
  }

  public static List<Model.Document> loadArticlesWithLocation(Document doc, List<String> locs) {
    List<Model.Document> articles = new ArrayList<>();

    Elements reuters = doc.select("REUTERS");

    for (Element elem : reuters) {
      String places = elem.select("PLACES > D").text();
      List<String> placesList = new ArrayList<>(Arrays.asList(places.split("\\s+")));

      if (placesList.size() != 1 || !locs.contains(placesList.get(0))) {
        continue;
      }

      //      String title = elem.select("TITLE").text();
      String text = elem.select("INNER").text();

      Model.Document art = new Model.Document();
      art.setRawText(text);
      art.setLabels("locations", placesList);
      articles.add(art);
    }
    return articles;
  }
}
