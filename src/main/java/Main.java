import model.Article;
import model.Corpus;
import model.Document;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;


public class Main {

    public static void main(String[] args) throws IOException {

        List<Article> articles = DataLoader.loadFromDir("/home/arch/IdeaProjects/ksr/resources/");

        List<String> locations = Arrays.asList("west-germany", "france", "uk", "canada", "japan", "usa");

        articles = DataLoader.filterArticlesByLocation(articles, locations);

        List<model.Document> documents = DocumentsFactory.documentsFromArticles(articles);

        //      split data to training and test sets
        int idx = Math.round(0.6f * documents.size());
        List<model.Document> trainingData = documents.subList(0, idx);
        List<model.Document> testData = documents.subList(idx, documents.size());

        System.out.println(testData.size());
        //      Add documents from training data to corpus
        Corpus corpus = new Corpus();
        Corpus testCorpus = new Corpus();

//        trainingData.forEach(corpus::addDocument);

        for (model.Document dc : trainingData) {
            corpus.addDocument(dc);
        }

        for (model.Document dc : testData) {
            testCorpus.addDocument(dc);
        }

// create new stop list
        Map<String, Integer> k = Vectorizer.sortByValue(corpus.getWordCounter());
        List<String> newStopWords = Vectorizer.getMostCommonWords(k, 0.5f);
//        remove custom words from created stop list
        newStopWords.remove("american");

//        delete new stop words from documents in corpus
        corpus.removeStopWordsFromDocuments(newStopWords);

        testCorpus.removeStopWordsFromDocuments(newStopWords);

        corpus.generateIDFs();
        testCorpus.generateIDFs();



        List<iFeatureExtractor> featureExtractors = new LinkedList<>();
        Map<String, List<String>> cuttedkeywords = new HashMap<>();

        for(String loc: locations){
            List<String> keys = new LinkedList<>();

            for (model.Document doc : corpus.getDocuemntsWithLabel(loc)) {
                keys.addAll(doc.getTokens());
            }
            Map<String, Float> keywords = FeatureExtractor.extractKeyWords(keys, corpus);

            List<String> cuttedKeyword = Vectorizer.getMostCommonWords(keywords, 5.0f);
            cuttedkeywords.put(loc, cuttedKeyword);
            System.out.println(cuttedKeyword);

            KeyWordSetFeature ks = new KeyWordSetFeature(keywords, loc);
            featureExtractors.add(ks);
        }

//        List<String> franceKeys = new LinkedList<>();
//
//        for (model.Document doc : corpus.getDocuemntsWithLabel("usa")) {
//            franceKeys.addAll(doc.getTokens());
//        }

//        KeyWordSetFeature ks = new KeyWordSetFeature(FeatureExtractor.extractKeyWords(franceKeys, corpus), "dfdf");

//        good
//        for(KeyWordSetFeature fe: featureExtractors){
////            System.out.println(fe.getLabel());
//            System.out.println(fe.getFeatureValue(Arrays.asList("gold", "mine", "ton", "feet")).getKey());
//        }

        Predictor pred = new Predictor(corpus, featureExtractors);

//        List<Document> germany = corpus.getDocuemntsWithLabel("japan");

//        for(model.Document doc: testCorpus.getDocuments()){
//            for(iFeatureExtractor fex: featureExtractors){
//                doc.setFeature(fex.getFeatureValue(doc.getTokens()));
//            }
//        }

        int correct = 0;

        for(int i =0; i<testCorpus.getDocuments().size(); i++){
//            System.out.println("========");
//            System.out.println(testCorpus.getDocument(i).getLabels());
//            System.out.println("========");
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




