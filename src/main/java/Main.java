import model.Article;
import model.Corpus;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) throws IOException {

        List<Article> articles = DataLoader.loadFromDir("/home/arch/IdeaProjects/ksr/resources/");

        List<String> locations = Arrays.asList("west-germany", "usa", "france", "uk", "canada", "japan");

        articles = DataLoader.filterArticlesByLocation(articles, locations);

        List<model.Document> documents = DocumentsFactory.documentsFromArticles(articles);

        //      split data to training and test sets
        int idx = Math.round(0.6f * documents.size());
        List<model.Document> trainingData = documents.subList(0, idx);
        List<model.Document> testData = documents.subList(idx, documents.size());

        System.out.println(testData.size());
        //      Add documents from training data to corpus
        Corpus corpus = new Corpus();
//        trainingData.forEach(corpus::addDocument);

        for (model.Document dc : trainingData) {
            corpus.addDocument(dc);
        }


// create new stop list
        Map<String, Integer> k = Vectorizer.sortByValue(corpus.getWordCounter());
        List<String> newStopWords = Vectorizer.getMostCommonWords(k, 0.5f);
//        remove custom words from created stop list
        newStopWords.remove("american");

//        delete new stop words from documents in corpus
        corpus.removeStopWordsFromDocuments(newStopWords);

        corpus.generateIDFs();



        List<KeyWordSetFeature> featureExtractors = new LinkedList<>();
        for(String loc: locations){
            List<String> keys = new LinkedList<>();

            for (model.Document doc : corpus.getDocuemntsWithLabel(loc)) {
                keys.addAll(doc.getTokens());
            }
            KeyWordSetFeature ks = new KeyWordSetFeature(FeatureExtractor.extractKeyWords(keys, corpus), loc);
            featureExtractors.add(ks);
        }

//        List<String> franceKeys = new LinkedList<>();
//
//        for (model.Document doc : corpus.getDocuemntsWithLabel("usa")) {
//            franceKeys.addAll(doc.getTokens());
//        }

//        KeyWordSetFeature ks = new KeyWordSetFeature(FeatureExtractor.extractKeyWords(franceKeys, corpus), "dfdf");

        for(KeyWordSetFeature fe: featureExtractors){
            System.out.println(fe.getLabel());
            System.out.println(fe.getFeatureValue(Arrays.asList("gold", "mine", "ton", "feet")).getValue());
        }
    }
}

