import Model.Corpus;
import Model.Document;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Utility.Preprocessing.*;

public class TestPreprocessing {
    @Test
    public void removeNonLetterCharsTest() {
        String before = "Hello! this is 1 test. o_0 l0l";
        Document doc = new Document();
        doc.setRawText(before);

        String before2 = "b123.///c";
        Document doc2 = new Document();
        doc2.setRawText(before2);

        Corpus corpus = new Corpus();
        corpus.add(doc);
        corpus.add(doc2);

        removeNonLetterChars(corpus);

        Assert.assertEquals("Hello this is test o l l", corpus.getDocuments().get(0).getRawText());
        Assert.assertEquals("b c", corpus.getDocuments().get(1).getRawText());
    }

    @Test
    public void tokenizeTest() {
        String before = "coffee machine not working";
        Document doc = new Document();
        doc.setRawText(before);

        Corpus corpus = new Corpus();
        corpus.add(doc);

        tokenize(corpus);

        Assert.assertEquals(Arrays.asList("coffee", "machine", "not", "working"),
                corpus.getDocuments().get(0).getTokens());
    }

    @Test
    public void toLowerCaseTest() {
        String before = "The BEST algorithm 12 !";
        Document doc = new Document();
        doc.setRawText(before);

        Corpus corpus = new Corpus();
        corpus.add(doc);

        toLowerCase(corpus);

        Assert.assertTrue("the best algorithm 12 !".equals(
                corpus.getDocuments().get(0).getRawText()));
    }


    @Test
    public void removeStopWordsTest() {

        List<String> stopWords = new ArrayList<>(
                Arrays.asList("i", "am", "the", "best", "also", "machine", "to"));

        String before = "hello i am the best washing machine programmer";
        Document doc = new Document();
        doc.setRawText(before);

        String before2 = "i also can code coffee machine connected to internet";
        Document doc2 = new Document();
        doc2.setRawText(before2);

        Corpus corpus = new Corpus();
        corpus.add(doc);
        corpus.add(doc2);

        tokenize(corpus);

        removeStopWords(corpus, stopWords);

        Assert.assertTrue(Arrays.asList("hello", "washing", "programmer")
                .equals(corpus.getDocuments().get(0).getTokens()));
        Assert.assertTrue(Arrays.asList("can", "code", "coffee", "connected", "internet")
                .equals(corpus.getDocuments().get(1).getTokens()));
    }

    @Test
    public void stemWordsTest() {

        String before = "working sometimes studies";
        Document doc = new Document();
        doc.setRawText(before);

        String before2 = "words matters money teaching";
        Document doc2 = new Document();
        doc2.setRawText(before2);

        Corpus corpus = new Corpus();
        corpus.add(doc);
        corpus.add(doc2);

        tokenize(corpus);

        stemWords(corpus);

        Assert.assertTrue(Arrays.asList("work", "sometim", "studi")
                .equals(corpus.getDocuments().get(0).getTokens()));

        Assert.assertEquals(Arrays.asList("word", "matter", "monei", "teach")
                , corpus.getDocuments().get(1).getTokens());
    }
}
