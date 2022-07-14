package com.github.zipcodewilmington.casino.games.dicegames;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.GamblingGameInterface;
import com.github.zipcodewilmington.casino.MultiplayerGamblingGame;
import com.github.zipcodewilmington.casino.Player;

import java.util.HashSet;
import java.util.Scanner;

public class HighLowDice implements MultiplayerGamblingGame {

    private HashSet<HighLowDicePlayer> players;
    private final Scanner scan = new Scanner(System.in);
    private final Dice dice = new Dice(2);
    private String winningBet;

    public HighLowDice(HashSet<HighLowDicePlayer> players) {
        this.players = players;
    }

    @Override
    public void beginGame() {
        printInstructions();
        for (HighLowDicePlayer player : players) {
            String username = player.getAccount().getUserName();
            int betAmount = printAndScanInt("Player " + username + " please input the number of tokens you would like to wager");
            player.placeBet(betAmount);
            scan.nextLine();
            String bet = printAndScanStr("Player " + username + " please place your bet (high, low or seven)").toLowerCase();
            player.bet(bet);
            while (player.getBetAmount() == -1 || player.getBet() == "invalid") {
                if (player.getBetAmount() == -1) {
                    betAmount = printAndScanInt("Invalid bet amount! Player " + username + " please input the number of tokens you would like to wager");
                    player.placeBet(betAmount);
                    scan.nextLine();
                }
                if (player.getBet() == "invalid") {
                    bet = printAndScanStr("Invalid bet! Player " + username + " please place your bet (high, low or seven)").toLowerCase();
                    player.bet(bet);
                }
            }
        }
        getWinningBet();
        //payout();
    }

    public void getWinningBet() {
        int roll = this.dice.tossAndSum();
        System.out.println("The dice roll is: " + roll + "!");
        if (roll < 7) this.winningBet = "low";
        else if (roll > 7) this.winningBet = "high";
        else this.winningBet = "seven";
        System.out.println("The winning bet is: " + this.winningBet + "!");
    }

    @Override
    public void payout(Account account, int payoutAmount) {
        for (HighLowDicePlayer player : players) {
            if (player.getBet().equals(this.winningBet)) {
                System.out.println("Congratulations " + player.getAccount().getUserName() + " on winning " + player.getBetAmount() + " tokens!");
                player.getAccount().addBalance(player.getBetAmount());
            } else {
                System.out.println("Sorry " + player.getAccount().getUserName() + " unfortunately you lost " + player.getBetAmount() + " tokens! :(");
                player.getAccount().deductBalance(player.getBetAmount());
            }
        }
    }

    @Override
    public String printInstructions() {
        return "Welcome to high low game! \n" +
                "Blah blah";
    }

    public String printAndScanStr(String s) {
        System.out.print(s + ": ");
        return scan.nextLine();
    }

    public int printAndScanInt(String s) {
        System.out.print(s + ": ");
        return scan.nextInt();
    }

    @Override
    public HashSet<Player> decideWinner(HashSet<Player> players) {
        return null;
    }
}
