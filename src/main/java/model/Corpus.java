package model;

import java.util.*;

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

        Set<String> wordsSet = new HashSet<>(doc.getTokens());

        for(String word: wordsSet){
            if(DocumentsWithWord.containsKey(word)){
                DocumentsWithWord.put(word, DocumentsWithWord.get(word) + 1);
            }

            else{
                DocumentsWithWord.put(word, 1);
            }
        }

        for(String word: doc.getTokens()){
            if(wordCounter.containsKey(word)){
                wordCounter.put(word, wordCounter.get(word) + 1);
            }
            else{
                wordCounter.put(word, 1);
            }
        }

        this.documents.add(doc);
    }

    public Document getDocument(Integer index){
        return this.documents.get(index);
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public Map<String, Integer> getWordCounter() {
        return wordCounter;
    }

    public Map<String, Integer> getDocumentsWithWord() {
        return DocumentsWithWord;
    }
}
