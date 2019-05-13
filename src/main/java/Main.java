import FeatureExtractors.*;
import dataLoading.DataLoader;
import dataLoading.DocumentsFactory;
import dataLoading.Vectorizer;
import metrics.EuclideanMetric;
import model.Article;
import model.Corpus;

import java.io.IOException;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {

        List<Article> articles = DataLoader.loadFromDir("/home/arch/IdeaProjects/ksr/resources/");

        List<String> locations = Arrays.asList("west-germany", "france", "uk", "canada", "japan", "usa");

        articles = DataLoader.filterLocation(articles, locations);

        List<model.Document> documents = DocumentsFactory.documentsFromArticles(articles);

        //      split data to training and test sets
        int idx = Math.round(0.6f * documents.size());
        List<model.Document> trainingData = documents.subList(0, idx);
        List<model.Document> testData = documents.subList(idx, documents.size());

        System.out.println("Size: " + testData.size());

        //      Add documents from training data to corpus
        Corpus corpus = new Corpus();
        trainingData.forEach(corpus);

        Corpus testCorpus = new Corpus();
        testData.forEach(testCorpus);


// create new stop list
        Map<String, Integer> k = Vectorizer.sortByValue(corpus.getWordCounter());
        List<String> newStopWords = Vectorizer.getMostCommonWords(k, 0.5f);

//        delete new stop words from documents in corpus
        corpus.removeStopWordsFromDocuments(newStopWords);

        testCorpus.removeStopWordsFromDocuments(newStopWords);

        corpus.generateIDFs();
        testCorpus.generateIDFs();


//        System.out.println("Ameryka: " + testCorpus.getDocuemntsWithLabel("usa").size());

        List<iFeatureExtractor> featureExtractors = new LinkedList<>();
        Map<String, List<String>> cuttedkeywords = new HashMap<>();

// Dla kazdej lokacji
        for(String loc: locations){
            List<String> keys = new LinkedList<>();
// Dla dokumentu z dana etykieta lokacji
            for (model.Document doc : corpus.getDocuemntsWithLabel(loc)) {
                keys.addAll(doc.getTokens());
            }
            Map<String, Float> keywords = FeatureExtractor.extractKeyWords(keys, corpus);

//            List<String> cuttedKeyword = Vectorizer.getMostCommonWords(keywords, 5.0f);
//            cuttedkeywords.put(loc, cuttedKeyword);
//            System.out.println(cuttedKeyword);

            KeyWordSetFeature ks = new KeyWordSetFeature(keywords, loc);
//            KeyWordDenseFeature ks2 = new KeyWordDenseFeature(new ArrayList<>(keywords.keySet()), loc);
            System.out.println("pass");
            featureExtractors.add(ks);
//            featureExtractors.add(ks2);
        }

        featureExtractors.add(new RepeatedWordFeature());
        featureExtractors.add(new LengthFeature(corpus));


//        List<String> franceKeys = new LinkedList<>();
//
//        for (model.Document doc : corpus.getDocuemntsWithLabel("usa")) {
//            franceKeys.addAll(doc.getTokens());
//        }


        Predictor pred = new Predictor(corpus, featureExtractors, new EuclideanMetric());

        int correct = 0;

        for(int i =0; i<testCorpus.getDocuments().size(); i++){
            String out = pred.predict(testCorpus.getDocument(i), 5);
            System.out.print(out + ' ');
            System.out.println(testCorpus.getDocument(i).getLabels().get("locations").get(0));
            if(out.equals(testCorpus.getDocument(i).getLabels().get("locations").get(0))){
                correct++;
            }
        }
        System.out.println(correct);
        System.out.println(testCorpus.getDocumentsCount());
        System.out.println((float)correct / testCorpus.getDocumentsCount());
    }
}




