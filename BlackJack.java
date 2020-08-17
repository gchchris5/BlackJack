package com.henderson.blackjack;

import java.util.Scanner;

public class BlackJack {

    public static void main(String[] args) {

        System.out.println("Welcome to BlackJack!");

        Deck deck = new Deck();
        deck.createDeck();
        deck.shuffle();

        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        double userMoney = 100.00;

        Scanner input = new Scanner(System.in);

        while (userMoney > 0) {
            System.out.println("You have $" + userMoney + ", how much would you like to bet?");
            double userBet = input.nextDouble();
            if (userBet > userMoney) {
                System.out.println("You don't have the required funds, get lost!");
                break;
            }

            boolean endRound = false;

            playerDeck.draw(deck);
            playerDeck.draw(deck);

            dealerDeck.draw(deck);
            dealerDeck.draw(deck);

            while(true) {
                System.out.println("Your hand: ");
                System.out.println(playerDeck.toString());
                System.out.println("Your hand is valued at: " + playerDeck.cardsValue());

                System.out.println("Dealer Hand: " + dealerDeck.getCard(0).toString() + " [hidden]");

                System.out.println("(1)Hit or (2)Stand?");
                int response = input.nextInt();

                if (response == 1) {
                    playerDeck.draw(deck);
                    System.out.println("You drew a: " + playerDeck.getCard(playerDeck.deckSize() - 1).toString());
                    if (playerDeck.cardsValue() > 21) {
                        System.out.println("Bust. Hand is currently valued at " + playerDeck.cardsValue());
                        userMoney -= userBet;
                        endRound = true;
                        break;
                    }
                }
                if (response == 2) {
                    break;
                }
            }

            System.out.println("Dealer Cards: " + dealerDeck.toString());
            if (dealerDeck.cardsValue() > playerDeck.cardsValue() && endRound == false) {
                System.out.println("Dealer Wins");
                userMoney -= userBet;
                endRound = true;
            }
            while (dealerDeck.cardsValue() < 17 && endRound == false) {
                dealerDeck.draw(deck);
                System.out.println("Dealer drew a: " + dealerDeck.getCard(dealerDeck.deckSize() - 1).toString());
            }

            System.out.println("Dealer's hand is valued at: " + dealerDeck.cardsValue());

            if (dealerDeck.cardsValue() > 21 && endRound == false) {
                System.out.println("Dealer busts! User wins!");
                userMoney += userBet;
                endRound = true;
            }
            if (playerDeck.cardsValue() == dealerDeck.cardsValue() && endRound == false) {
                System.out.println("Push!");
                endRound = true;
            }

            if (playerDeck.cardsValue() > dealerDeck.cardsValue() && endRound == false) {
                System.out.println("User wins!");
                userMoney += userBet;
                endRound = true;
            }

            else if (endRound == false) {
                System.out.println("Dealer Wins");
                userMoney -= userBet;
                endRound = true;
            }

            playerDeck.moveCardsToDeck(deck);
            dealerDeck.moveCardsToDeck(deck);
            System.out.println("End of Hand.");

        }

        System.out.println("You went broke!");

    }
}
