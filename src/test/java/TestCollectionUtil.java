import static Utility.CollectionUtil.splitListByPercent;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

public class TestCollectionUtil {

    @Test
    public void splitEvenList() {
        List<Integer> list = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        Pair<List<Integer>, List<Integer>> pair = splitListByPercent(list, 50);
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4), pair.getKey());
        Assert.assertEquals(Arrays.asList(5, 6, 7, 8), pair.getValue());

        pair = splitListByPercent(list, 20);

        Assert.assertEquals(Collections.singletonList(1), pair.getKey());
        Assert.assertEquals(Arrays.asList(2, 3, 4, 5, 6, 7, 8), pair.getValue());
    }

    @Test
    public void splitOddList() {
        List<Integer> list = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        Pair<List<Integer>, List<Integer>> pair = splitListByPercent(list, 50);

        Assert.assertEquals(pair.getKey(), Arrays.asList(1, 2, 3));
        Assert.assertEquals(pair.getValue(), Arrays.asList(4, 5, 6, 7));

        pair = splitListByPercent(list, 30);

        Assert.assertEquals(Arrays.asList(1, 2), pair.getKey());
        Assert.assertEquals(Arrays.asList(3, 4, 5, 6, 7), pair.getValue());
    }
}
