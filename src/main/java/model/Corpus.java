package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Corpus {
    List<Document> documents;
    Map<String, Integer> wordCounter;
    Map<String, Integer> DocumentsWithWord;

    public Corpus() {
        this.documents = new ArrayList<>();
        this.wordCounter = new HashMap<>();
        this.DocumentsWithWord = new HashMap<>();
    }

    public void addDocument(Document doc){


        this.documents.add(doc);
    }

    public Document getDocument(Integer index){
        return this.documents.get(index);
    }
}
