package com.flayger;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class SortHandsTest {
    //сортировка возможных рук

    @Test
    public void sortDifferentHandTest() throws Exception {

        PokerHand handNone = new PokerHand("5S 6H AC JD TD");
        PokerHand handTwo = new PokerHand("AS 6H 2C QD AD");
        PokerHand handTwoTwo = new PokerHand("5S 5H AC AD TD");
        PokerHand handFour = new PokerHand("5S 5H 5C 5D AD");
        PokerHand handStraight = new PokerHand("4S 2H 5C 3D 6D");
        PokerHand handFullHouse = new PokerHand("3S JH 3C JD 3D");
        PokerHand handThree = new PokerHand("KS KH KC JD TD");
        PokerHand handStaightFlush = new PokerHand("4S 5S 6S 7S 8S");
        PokerHand handRoyalFlush = new PokerHand("AH KH QH JH TH");
        PokerHand handFlush = new PokerHand("2D 6D 9D 8D KD");


        ArrayList<PokerHand> hands = new ArrayList<PokerHand>();
        hands.add(handTwo);
        hands.add(handFour);
        hands.add(handStraight);
        hands.add(handFullHouse);
        hands.add(handThree);
        hands.add(handNone);
        hands.add(handTwoTwo);
        hands.add(handStaightFlush);
        hands.add(handFlush);
        hands.add(handRoyalFlush);

        Collections.sort(hands);

        ArrayList<PokerHand> expectedArray = new ArrayList<PokerHand>();
        expectedArray.add(handRoyalFlush);
        expectedArray.add(handStaightFlush);
        expectedArray.add(handFour);
        expectedArray.add(handFullHouse);
        expectedArray.add(handFlush);
        expectedArray.add(handStraight);
        expectedArray.add(handThree);
        expectedArray.add(handTwoTwo);
        expectedArray.add(handTwo);
        expectedArray.add(handNone);

        Assert.assertEquals("массив отсортирован неправильно", expectedArray, hands);
    }

    @Test
    public void sortSameHandTest() throws Exception {

        PokerHand handNone = new PokerHand("5S 6H AC JD TD");
        PokerHand handNoneSecond = new PokerHand("5S 6H 2C QD TD");
        PokerHand handStraight = new PokerHand("5S 6H 7C 8D 9D");
        PokerHand handStraightSecond = new PokerHand("5S 6H 7C 8D 4D");
        PokerHand handFullHouse = new PokerHand("3S JH 3C JD 3D");
        PokerHand handFullHouseSecond = new PokerHand("AS AH QC QD AD");
        PokerHand handFullHouseThird = new PokerHand("2S 2H JC JD 2D");
        PokerHand handRoyalFlush = new PokerHand("AH KH QH JH TH");
        PokerHand handStraightFlush = new PokerHand("2S 3S 4S 5S 6S");
        PokerHand handStraightFlushSecond = new PokerHand("5D 6D 7D 8D 9D");
        PokerHand handFlush = new PokerHand("2S 6S 4S 8S TS");
        PokerHand handFlushSecond = new PokerHand("2C 5C QC KC AC");

        ArrayList<PokerHand> hands = new ArrayList<>();
        hands.add(handNoneSecond);
        hands.add(handStraightSecond);
        hands.add(handStraight);
        hands.add(handNone);
        hands.add(handFullHouse);
        hands.add(handFullHouseSecond);
        hands.add(handFullHouseThird);
        hands.add(handRoyalFlush);
        hands.add(handStraightFlush);
        hands.add(handStraightFlushSecond);
        hands.add(handFlush);
        hands.add(handFlushSecond);


        Collections.sort(hands);

        ArrayList<PokerHand> expectedArray = new ArrayList<>();
        expectedArray.add(handRoyalFlush);

        expectedArray.add(handStraightFlushSecond);
        expectedArray.add(handStraightFlush);

        expectedArray.add(handFullHouseSecond);
        expectedArray.add(handFullHouse);
        expectedArray.add(handFullHouseThird);

        expectedArray.add(handFlushSecond);
        expectedArray.add(handFlush);

        expectedArray.add(handStraight);
        expectedArray.add(handStraightSecond);

        expectedArray.add(handNone);
        expectedArray.add(handNoneSecond);
        Assert.assertEquals("массив отсортирован неправильно", expectedArray, hands);
    }
}
