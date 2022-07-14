package com.github.zipcodewilmington.casino.games.cardgames;

import com.github.zipcodewilmington.casino.*;

import java.util.List;

public class ThreeCardPokerPlayer extends Player implements GamblingPlayer{
    private List<Card> playerHand;

    public ThreeCardPokerPlayer(Account account) {
        super(account);
    }

    @Override
    public String getPlayerName() {
        return account.getUserName();
    }

    @Override
    public Account getPlayerAccount() {
        return account;
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }
    public void setPlayerHand(List<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public int playOrFold() {
        return 0; // TODO do we need this method? or should user input call play and fold methods separately
    }

    @Override
    public void placeBet(int amount) {
        // TODO implement betting for 3 card poker
    }
}
