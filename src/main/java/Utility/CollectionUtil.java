package Utility;

import java.util.List;
import javafx.util.Pair;

public class CollectionUtil {

    public static <T> Pair<List<T>, List<T>> splitListByPercent(List<T> list, int percent) {
        int cutIdx = (list.size() * percent) / 100;
        List<T> firstList = list.subList(0, cutIdx);
        List<T> secondList = list.subList(cutIdx, list.size());
        return new Pair<>(firstList, secondList);
    }
}
