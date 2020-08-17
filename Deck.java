package com.henderson.blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> deckOfCards;

    public Deck() {
        this.deckOfCards = new ArrayList<Card>();
    }

    public void createDeck() {
        for(Suit cardSuit : Suit.values()) {
            for(Value cardValue : Value.values()) {
                this.deckOfCards.add(new Card(cardSuit, cardValue));
            }
        }
    }

    //Generate random index - random.nextInt((max - min) + 1) + min
    public void shuffle() {
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        Random random = new Random();
        int randomIndex = 0;
        int originalSize = this.deckOfCards.size();
        for (int i = 0; i < originalSize; i++) {
            randomIndex = random.nextInt((this.deckOfCards.size() - 1 - 0) + 1) + 0;
            tempDeck.add(this.deckOfCards.get(randomIndex));
            this.deckOfCards.remove(randomIndex);
        }
        this.deckOfCards = tempDeck;
    }

    public String toString() {
        String output = "";
        for (Card a : this.deckOfCards) {
            output += "\n" + a.toString();
        }
        return output;
    }

    public void removeCard(int index) {
        this.deckOfCards.remove(index);
    }

    public Card getCard(int index) {
        return this.deckOfCards.get(index);
    }

    public void addCard(Card cardIn) {
        this.deckOfCards.add(cardIn);
    }

    public void draw(Deck deckIn) {
        this.deckOfCards.add(deckIn.getCard(0));
        deckIn.removeCard(0);
    }

    public int cardsValue() {
        int totalValue = 0;
        int aces = 0;

        for (Card a : this.deckOfCards) {
            switch (a.getValue()) {
                case TWO: totalValue += 2;
                    break;
                case THREE: totalValue += 3;
                    break;
                case FOUR: totalValue += 4;
                    break;
                case FIVE: totalValue += 5;
                    break;
                case SIX: totalValue += 6;
                    break;
                case SEVEN: totalValue += 7;
                    break;
                case EIGHT: totalValue += 8;
                    break;
                case NINE: totalValue += 9;
                    break;
                case TEN: totalValue += 10;
                    break;
                case JACK: totalValue += 10;
                    break;
                case QUEEN: totalValue += 10;
                    break;
                case KING: totalValue += 10;
                    break;
                case ACE: aces += 1;
                    break;
            }
        }
        for (int i = 0; i < aces; i++) {
            if (totalValue > 10) {
                totalValue += 1;
            }
            else {
                totalValue += 11;
            }
        }
        return totalValue;
    }

    public int deckSize() {
        return this.deckOfCards.size();
    }

    public void moveCardsToDeck(Deck moveDeck) {
        int thisDeckSize = this.deckOfCards.size();

        for (int i = 0; i < thisDeckSize; i++) {
            moveDeck.addCard(this.getCard(i));
        }
        for (int i = 0; i < thisDeckSize; i++) {
            this.removeCard(0);
        }
    }


}
