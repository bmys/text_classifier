package model;

import java.util.*;

public class Corpus {
    private List<Document> documents;
    private Map<String, Integer> wordCounter;
    private Map<String, Integer> DocumentsWithWord;
    private Map<String, Float> wordIDF;

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

    public List<Document> getDocuemntsWithLabel(String label){
        List<Document> docs = new LinkedList<>();
        for(Document doc: this.documents){
            if(doc.getLabels().get("locations").contains(label)){
                docs.add(doc);
            }
        }
        return docs;
    }

    public Map<String, Float> getWordIDF() {
        return wordIDF;
    }

    public void removeStopWordsFromDocuments(List<String> stopWords){
        for (Document document : this.documents) {
            document.removeTokens(stopWords);
        }
    }

    public void generateIDFs(){
        Map<String, Float> idfs = new HashMap<>();
        int size = this.DocumentsWithWord.size();
        for(Map.Entry<String, Integer> entry: this.DocumentsWithWord.entrySet() ){
            if(entry.getValue() == 0) continue;
            double k = Math.log((size * 1.0d) / (entry.getValue() * 1.0d));
            idfs.put(entry.getKey(), (float)k);
        }
        this.wordIDF = idfs;
    }

}
