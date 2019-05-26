package Utility.DataLoader;

import Model.Document;
import com.google.common.io.Closer;
import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoadFromPlaneText {

    public static List<String> LoadFromPlaneText(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<String> loadFromResource(String fileName) {
        String fileContent = readResource(fileName);
        String lines[] = fileContent.split("\\r?\\n");
        return Arrays.asList(lines);
    }

    private static String readResource(final String fileName) {
        Closer closer = Closer.create();
        try {
            URL url = Resources.getResource(fileName);
            return Resources.toString(url, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static List<Document> getDocumentsFromPlainText() {
        List<Document> documents = new ArrayList<>();
        List<String> java = loadFromResource("javaSet");
        List<String> python = loadFromResource("pythonSet");

        for (String s : java) {
            Document doc = new Document();
            doc.setRawText(s);
            doc.setLabels("lang", Arrays.asList("java"));
            documents.add(doc);
            //
        }
        for (String s : python) {
            Document doc = new Document();
            doc.setRawText(s);
            doc.setLabels("lang", Arrays.asList("python"));
            documents.add(doc);
            //
        }

        return documents;
    }
}
