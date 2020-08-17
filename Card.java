package com.henderson.blackjack;

public class Card {

    private Suit suit;
    private Value value;

    public Card(Suit suitIn, Value valueIn) {
        this.suit = suitIn;
        this.value = valueIn;
    }

    public String toString() {
        return this.suit.toString() + " - " + this.value.toString();
    }

    public Value getValue() {
        return this.value;
    }
}
