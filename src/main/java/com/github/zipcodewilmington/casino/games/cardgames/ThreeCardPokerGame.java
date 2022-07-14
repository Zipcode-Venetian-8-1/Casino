package com.github.zipcodewilmington.casino.games.cardgames;

import com.github.zipcodewilmington.casino.*;

import java.util.*;

public class ThreeCardPokerGame implements MultiplayerGamblingGame {

    private Deck deck;
    private HashSet<ThreeCardPokerPlayer> playerSet;
    private List<Card> dealerHand;
    private final int ante = 5;
    private final int betAmt = 20;
    public Scanner scan;

    public ThreeCardPokerGame(HashSet<ThreeCardPokerPlayer> playerSet) {
        this.deck = new Deck();
        this.playerSet = playerSet;
        this.dealerHand = new ArrayList<>();
    }

    public HashSet<ThreeCardPokerPlayer> getPlayerSet() {
        return playerSet;
    }

    public List<Card> dealHand() {
        List<Card> threeCardHand = new ArrayList<>();
        threeCardHand.add(deck.dealTop());
        threeCardHand.add(deck.dealTop());
        threeCardHand.add(deck.dealTop());
        return threeCardHand;
    }

    public StringBuilder flipAllCards() {
        StringBuilder allCards = new StringBuilder(" * * Dealer's Hand * *\n");
        for(Card card : dealerHand) {
            allCards.append(card).append("\n");
        }
        for(ThreeCardPokerPlayer player : playerSet) {
            allCards.append(" * * ").append(player.getPlayerName()).append("'s Hand * *\n");
            for(Card card : player.getPlayerHand()) {
                allCards.append(card).append("\n");
            }
        }

        return allCards;
    }

    @Override
    public void beginGame() {
        System.out.println(printInstructions());
        while (true) {
            /*
                for each player:
                    place ante or return to lobby
                    remove from playerSet if they leave game
             */
            for (ThreeCardPokerPlayer player : playerSet) {
                int playerInput = getNumber(player.getPlayerName() + ": (1) Place Ante  (2) Return to Lobby");
                if (playerInput == 1) {
                    //player.getPlayerAccount().deductBalance(ante);
                } else if (playerInput == 2) {
                    playerSet.remove(player);
                }
            }
            if (playerSet.isEmpty()) break; // exit game


            // deal the dealer in and all players remaining who have anted
            dealerHand = dealHand();
            for (ThreeCardPokerPlayer player : playerSet) {
                player.setPlayerHand(dealHand());
            }

            // ask for bet
            //    flag players as folded if they don't place further bet

            System.out.println(flipAllCards());
            // determine winner for each player and add to balance with payout()
            // for each winning 3cpokerplayer payout(player.getAccount(), $$)
            //     display payouts
        }
    }

    @Override
    public String printInstructions() {
        return "----------------------------------\n" +
               "---Welcome to Three Card Poker----\n" +
               "----------------------------------\n" +
               "I don't know the rules, good luck.";
    }

    @Override
    public HashSet<Player> decideWinner(HashSet<Player> players) {
        return null;
    }

    @Override
    public void payout(Account account, int payoutAmount) {
        account.setBalance(account.getBalance() + payoutAmount);
    }

    public int getNumber(String message) {
        scan = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            try {
                int input = scan.nextInt();
                if (input < 0) continue;
                return input;
            }
            catch (InputMismatchException e) {
                System.out.println("\"" + scan.next() + "\" isn't a number!");
            }
        }
    }
}
