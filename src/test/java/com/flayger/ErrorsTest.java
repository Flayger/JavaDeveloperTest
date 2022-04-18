package com.flayger;

import org.junit.Assert;
import org.junit.Test;

public class ErrorsTest {

    @Test
    public void InitErrorTest() throws Exception {
        Exception thrown = Assert.assertThrows(Exception.class, () ->
            new PokerHand("KSS 2H 5C JD TD"));
        Assert.assertTrue(thrown.getMessage().contains("Неправильная карта, размер"));
    }

    @Test
    public void WrongSuitErrorTest() throws Exception {
        Exception thrown = Assert.assertThrows(Exception.class, () ->
                new PokerHand("KZ 2H 5C JD TD"));
        Assert.assertTrue(thrown.getMessage().contains("Неправильная масть карты: "));
    }

    @Test
    public void WrongCardErrorTest() throws Exception {
        Exception thrown = Assert.assertThrows(Exception.class, () ->
                new PokerHand("LS 2H 5C JD TD"));
        Assert.assertTrue(thrown.getMessage().contains("Неправильный ранг карты: "));
    }

    @Test
    public void HandSizeErrorTest() throws Exception {
        Exception thrown = Assert.assertThrows(Exception.class, () ->
                new PokerHand("JS 2H 5C JD JD JD"));
        Assert.assertTrue(thrown.getMessage().contains("Неправильное количество карт в руке"));
    }


}
