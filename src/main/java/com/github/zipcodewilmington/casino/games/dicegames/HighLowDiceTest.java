package com.github.zipcodewilmington.casino.games.dicegames;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.AccountManager;

import java.io.IOException;
import java.util.HashSet;

public class HighLowDiceTest {
    public static void main(String[] args) throws IOException {

        AccountManager mgr = new AccountManager();
        mgr.createAccount("goose", "omgimagoose");
        mgr.createAccount("duck", "omgimaduck");

        HashSet<HighLowDicePlayer> players = new HashSet<>();

        HighLowDicePlayer goose = new HighLowDicePlayer(mgr.getAccount("goose", "omgimagoose"));
        HighLowDicePlayer duck = new HighLowDicePlayer(mgr.getAccount("duck", "omgimaduck"));

        players.add(goose);
        players.add(duck);

        HighLowDice game = new HighLowDice(players);
        game.beginGame();

        mgr.updateAccounts();
    }
}
