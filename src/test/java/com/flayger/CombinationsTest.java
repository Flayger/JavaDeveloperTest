package com.flayger;

import org.junit.Assert;
import org.junit.Test;

public class CombinationsTest {
    //все варианты комбинаций
    //двойки
    //тройки
    //четверки
    //фул хаус
    //стрейт
    //без комбинации
    //правильность определения

    @Test
    public void createTwoHandTest() throws Exception {
        String exampleHand = "KS 2H 5C JD KD";
        PokerHand hand = new PokerHand(exampleHand);
        Assert.assertTrue("Рука должна иметь комбинацию пара", hand.getCombination().equals(Combinations.TWO));
    }

    @Test
    public void createTwoTwoHandTest() throws Exception {
        String exampleHand = "KS 2H 5C 2D KD";
        PokerHand hand = new PokerHand(exampleHand);
        Assert.assertTrue("Рука должна иметь комбинацию 2 пары", hand.getCombination().equals(Combinations.TWOTWO));
    }

    @Test
    public void createThreeHandTest() throws Exception {
        String exampleHand = "2S 2H 2C JD KD";
        PokerHand hand = new PokerHand(exampleHand);
        Assert.assertTrue("Рука должна иметь комбинацию тройка", hand.getCombination().equals(Combinations.THREE));
    }

    @Test
    public void createFourHandTest() throws Exception {
        String exampleHand = "JS JH JC JD KD";
        PokerHand hand = new PokerHand(exampleHand);
        Assert.assertTrue("Рука должна иметь комбинацию четверка", hand.getCombination().equals(Combinations.FOUR));
    }

    @Test
    public void createFullHouseHandTest() throws Exception {
        String exampleHand = "5S 5H 5C JD JD";
        PokerHand hand = new PokerHand(exampleHand);
        Assert.assertTrue("Рука должна иметь комбинацию фулл хаус", hand.getCombination().equals(Combinations.FULL_HOUSE));
    }

    @Test
    public void createStraightHandTest() throws Exception {
        String exampleHand = "5S 6H 7C 8D 9D";
        PokerHand hand = new PokerHand(exampleHand);
        Assert.assertTrue("Рука должна иметь комбинацию стрейт", hand.getCombination().equals(Combinations.STRAIGHT));
    }

    @Test
    public void createStraightFlushHandTest() throws Exception {
        String exampleHand = "2S 3S 4S 5S 6S";
        PokerHand hand = new PokerHand(exampleHand);
        Assert.assertTrue("Рука должна иметь комбинацию стрейт флэш", hand.getCombination().equals(Combinations.STRAIGHT_FLUSH));
    }

    @Test
    public void createRoyalFlushHandTest() throws Exception {
        String exampleHand = "TH JH QH KH AH";
        PokerHand hand = new PokerHand(exampleHand);
        Assert.assertTrue("Рука должна иметь комбинацию роял флэш", hand.getCombination().equals(Combinations.ROYAL_FLUSH));
    }

    @Test
    public void createFlushHandTest() throws Exception {
        String exampleHand = "2C 3C 4C 7C TC";
        PokerHand hand = new PokerHand(exampleHand);
        Assert.assertTrue("Рука должна иметь комбинацию флэш", hand.getCombination().equals(Combinations.FLUSH));
    }

    @Test
    public void createNoCombinationHandTest() throws Exception {
        String exampleHand = "2S 4H 6C JD QD";
        PokerHand hand = new PokerHand(exampleHand);
        Assert.assertTrue("Рука должна иметь пустую комбинацию", hand.getCombination().equals(Combinations.NONE));

    }




}
