package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.casino.games.cardgames.ThreeCardPokerGame;
import com.github.zipcodewilmington.casino.games.cardgames.ThreeCardPokerPlayer;
import org.junit.Assert;
import org.junit.Test;
import java.util.HashSet;

public class ThreeCardPokerGameTest {
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

        System.out.println(newGame.flipAllCards());
    }
}