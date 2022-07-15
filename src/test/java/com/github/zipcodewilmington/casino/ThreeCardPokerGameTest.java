package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.casino.games.cardgames.ThreeCardPokerGame;
import com.github.zipcodewilmington.casino.games.cardgames.ThreeCardPokerPlayer;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ThreeCardPokerGameTest {
    @Test
    public void dealHandTest() {
        Account account1 = new Account("Kyle", "p", 5000);
        Account account2 = new Account("Thina", "q", 8000);
        ThreeCardPokerPlayer player1 = new ThreeCardPokerPlayer(account1);
        ThreeCardPokerPlayer player2 = new ThreeCardPokerPlayer(account2);
        HashSet<ThreeCardPokerPlayer> playerSet = new HashSet<>();
        playerSet.add(player1);
        playerSet.add(player2);
        ThreeCardPokerGame newGame = new ThreeCardPokerGame(playerSet);
        Suit hearts = Suit.HEARTS;
        CardValue king = CardValue.KING;
        Card card1 = new Card(king, hearts);
        Suit spades = Suit.SPADES;
        CardValue nine = CardValue.NINE;
        Card card2 = new Card(nine, spades);
        Card card3 = new Card(nine, hearts);
        List<Card> threeCardHand = new ArrayList<>();
        threeCardHand.add(card3);
        threeCardHand.add(card2);
        threeCardHand.add(card1);
        newGame.discardHand(threeCardHand);
        List<Card> dealMeIn = newGame.dealHand();

        Assert.assertEquals(dealMeIn, threeCardHand);
    }
    @Test
    public void flipAllCardsTest() {
        Account account1 = new Account("Kyle", "p", 5000);
        Account account2 = new Account("Thina", "q", 8000);
        ThreeCardPokerPlayer player1 = new ThreeCardPokerPlayer(account1);
        ThreeCardPokerPlayer player2 = new ThreeCardPokerPlayer(account2);
        HashSet<ThreeCardPokerPlayer> playerSet = new HashSet<>();
        playerSet.add(player1);
        playerSet.add(player2);
        ThreeCardPokerGame newGame = new ThreeCardPokerGame(playerSet);

        //System.out.println(newGame.flipAllCards());
        newGame.beginGame();
    }
}
