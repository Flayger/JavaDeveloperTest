package com.flayger;

public enum ValidSuit {
    SPADES, CLUBS, HEARTS, DIAMONDS;

    public static ValidSuit fromCharacter(char c) throws Exception {
        switch (c) {
            case 'S':
                return SPADES;
            case 'C':
                return CLUBS;
            case 'H':
                return HEARTS;
            case 'D':
                return DIAMONDS;
        }
        throw new Exception("Неправильная масть карты: " + c);
    }
}
