import model.Article;
import model.Corpus;

import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
//        Document doc = DataLoader.loadSgml("/home/arch/IdeaProjects/ksr/resources/reut2-019.sgm");
        List<Article> articles = DataLoader.loadFromDir("/home/arch/IdeaProjects/ksr/resources/");
//        List<Article> articles = ArticleFactory.loadArticles(doc);

        List<String> locations = Arrays.asList("west-germany", "usa", "france", "uk", "canada", "japan");

        articles = DataLoader.filterArticlesByLocation(articles, locations);

        List<model.Document> documents = DocumentsFactory.documentsFromArticles(articles);

        //      split data to training and test sets
        int idx = Math.round(0.6f * documents.size());
        List<model.Document> trainingData = documents.subList(0, idx);
        List<model.Document> testData = documents.subList(idx, documents.size());

        //      Add documents from training data to corpus
        Corpus corpus = new Corpus();
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

        List<String> franceKeys = new LinkedList<>();

        for (model.Document doc : corpus.getDocuemntsWithLabel("canada")) {
            franceKeys.addAll(doc.getTokens());
        }

        System.out.println();
        KeyWordSetFeature ks = new KeyWordSetFeature(FeatureExtractor.extractKeyWords(franceKeys, corpus), "dfdf");
        System.out.println(ks.getFeatureValue(Arrays.asList("canada", "is", "mine", "ton", "feet")).getValue());
    }
}

