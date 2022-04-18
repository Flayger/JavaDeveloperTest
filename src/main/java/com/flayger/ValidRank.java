package com.flayger;

public enum ValidRank {

    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;

    public static ValidRank fromCharacter(char c) throws Exception {
        switch (c) {
            case '2':
                return TWO;
            case '3':
                return THREE;
            case '4':
                return FOUR;
            case '5':
                return FIVE;
            case '6':
                return SIX;
            case '7':
                return SEVEN;
            case '8':
                return EIGHT;
            case '9':
                return NINE;
            case 'T':
                return TEN;
            case 'J':
                return JACK;
            case 'Q':
                return QUEEN;
            case 'K':
                return KING;
            case 'A':
                return ACE;
        }
        throw new Exception("Неправильный ранг карты: " + c);
    }


}
