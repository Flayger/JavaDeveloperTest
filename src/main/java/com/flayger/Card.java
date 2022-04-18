package com.flayger;

public class Card {

    private ValidRank rank;
    private ValidSuit suit;

    public Card(String card) throws Exception {
        if (card.length() != 2)
            throw new Exception("Неправильная карта, размер");

        this.rank = ValidRank.fromCharacter(card.charAt(0));
        this.suit = ValidSuit.fromCharacter(card.charAt(1));
    }

    public ValidRank getRank() {
        return rank;
    }

    public ValidSuit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return "C[" +
                "r=" + rank +
                "|s=" + suit +
                ']';
    }
}
