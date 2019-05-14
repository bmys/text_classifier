package Model;

import javafx.util.Pair;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static Utility.CollectionUtil.splitListByPercent;

public class Corpus implements Collection<Document> {
    private LinkedList<Document> documents = new LinkedList<>();
    private String name = "";

    public Corpus() {
    }

    public Corpus(String name) {
        this.name = name;
    }

    public Corpus(List<Document> documents, String name) {
        this.documents.addAll(documents);
        this.name = name;
    }

    public HashMap<String, Corpus> splitCorpus(int percent, String firstName, String secondName) {
        Pair<List<Document>, List<Document>> corpuses = splitListByPercent(documents, percent);

        Corpus corpus = new Corpus(corpuses.getKey(), firstName);
        Corpus corpus2 = new Corpus(corpuses.getValue(), secondName);

        HashMap<String, Corpus> corpusesMap = new HashMap<>();
        corpusesMap.put(firstName, corpus);
        corpusesMap.put(secondName, corpus2);
        return corpusesMap;
    }

    @Override
    public int size() {
        return documents.size();
    }

    @Override
    public boolean isEmpty() {
        return documents.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return documents.contains(o);
    }

    @Override
    public Iterator<Document> iterator() {
        return documents.iterator();
    }

    @Override
    public Object[] toArray() {
        return documents.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return documents.toArray(ts);
    }

    @Override
    public boolean add(Document document) {
        return documents.add(document);
    }

    @Override
    public boolean remove(Object o) {
        return documents.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return documents.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends Document> collection) {
        return documents.addAll(collection);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return documents.removeAll(collection);
    }

    @Override
    public boolean removeIf(Predicate<? super Document> predicate) {
        return documents.removeIf(predicate);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return documents.retainAll(collection);
    }

    @Override
    public void clear() {
        documents.clear();
    }

    @Override
    public Spliterator<Document> spliterator() {
        return documents.spliterator();
    }

    @Override
    public Stream<Document> stream() {
        return documents.stream();
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = new LinkedList<>(documents);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
