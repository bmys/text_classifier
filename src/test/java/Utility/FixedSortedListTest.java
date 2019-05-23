//package Utility;
//
//import static org.junit.Assert.*;
//
//import javafx.util.Pair;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//public class FixedSortedListTest {
//
//
//  private FixedSortedList list;
//  private Pair<Double, String> pair1;
//  private Pair<Double, String> pair2;
//  private Pair<Double, String> pair3;
//  private Pair<Double, String> pair2other;
//
//  @Before
//  public void before(){
//    System.out.println("Setting it up!");
//    list = FixedSortedList.create(3, true);
//    pair1 = new Pair<>(1.0, "first");
//    pair2 = new Pair<>(2.0, "second");
//    pair3 = new Pair<>(3.0, "third");
//    pair2other = new Pair<>(2.0, "firstButOther");
//  }
//
//  @Test
//  public void fixedSizeTest(){
//    list.add(pair1);
//    list.add(pair2);
//    list.add(pair3);
//    list.add(pair2other);
//    list.add(pair2other);
//
//    Assert.assertEquals(3, list.size());
//  }
//}