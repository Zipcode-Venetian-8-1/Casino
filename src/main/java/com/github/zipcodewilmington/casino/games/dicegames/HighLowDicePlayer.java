package com.github.zipcodewilmington.casino.games.dicegames;
import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.GamblingPlayer;
import com.github.zipcodewilmington.casino.Player;

public class HighLowDicePlayer extends Player implements GamblingPlayer {

    private String bet;
    private int betAmount;

    public HighLowDicePlayer(Account account) {
        super(account);
    }

    @Override
    public String getPlayerName() {
        return null;
    }

    @Override
    public Account getPlayerAccount() {
        return null;
    }

    @Override
    public void placeBet(int amount) {
        if (this.account.checkBalance(amount)) {
            this.betAmount = amount;
        } else {
            this.betAmount = -1;
        }
    }

    public void bet(String bet) {
        if (bet.equals("high") || bet.equals("low") || bet.equals("seven")) this.bet = bet;
        else this.bet = "invalid";
    }

    public String getBet() {
        return this.bet;
    }

    public int getBetAmount() {
        return this.betAmount;
    }
}
