import java.util.ArrayList;
import java.util.List;

public class Article {
    private String title;
    private String text;
    private List locations;

     Article(String title, String text, List<String> locations) {
        this.title = title;
        this.text = text;
        this.locations = locations;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List getLocations() {
        return locations;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\n' +
                ", text='" + text + '\n' +
                ", locations=" + locations +
                '}';
    }
}
