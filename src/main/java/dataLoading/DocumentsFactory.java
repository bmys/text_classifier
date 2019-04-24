package dataLoading;

import model.Article;
import model.Document;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ca.rmen.porterstemmer.PorterStemmer;

public class DocumentsFactory {
    public static List<Document> documentsFromArticles(List<Article> articles){
        List<Document> documents = new LinkedList<>();
        List<String> stopWords = Preprocessing.loadStopWordsFromFile("/home/arch/IdeaProjects/bm/src/main/resources/stopwords");
        PorterStemmer stemmer = new PorterStemmer();

        for(Article art: articles){
            String articleText = art.getText().toLowerCase();
            articleText = Preprocessing.removeNonLetterChars(articleText);

            List<String> tokens = Vectorizer.string2vec(articleText);

            tokens = Preprocessing.removeStopWords(tokens, stopWords);

             for(int i=0; i < tokens.size(); i++){
                 tokens.set(i, stemmer.stemWord(tokens.get(i)));
             }

        Map<String, List<String>> labels = new HashMap<>();
        labels.put("locations", art.getLocations());

        Document doc = new Document(tokens, labels);
        documents.add(doc);
        }

        return documents;
    }
}
