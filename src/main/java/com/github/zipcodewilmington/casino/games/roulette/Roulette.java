package com.github.zipcodewilmington.casino.games.roulette;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.GamblingGameInterface;
import com.github.zipcodewilmington.casino.games.roulette.typeofbet.OddOrEven;
import com.github.zipcodewilmington.casino.games.roulette.typeofbet.RedOrBlack;
import com.github.zipcodewilmington.casino.games.roulette.typeofbet.StreetBet;
import com.github.zipcodewilmington.casino.games.roulette.utilroulette.IOConsoleReader;
import com.github.zipcodewilmington.utils.Sleep;


public class Roulette implements GamblingGameInterface {
    private  RoulettePlayer player;
    private final WheelSpin myWheel;
    public Roulette (RoulettePlayer player) {
        this.player = player;
        myWheel = new WheelSpin();
    }


    private final String R = "Roulette";
    private Bet[] myPossibleBets = {
            new RedOrBlack("Red or Black", 1),
            new OddOrEven("Odd or Even", 1),
            new StreetBet("Three in a Row", 11)
    };


    public String getAccount() {
        return null;
    }

    public void play(Account account) {
        printInstructions();

        while (true) {
                 {
                String username = player.getAccount().getUserName();
                System.out.println("(( Your current balance is " + player.getAccount().getBalance() + " tokens. ))\n\n");

                int amount = IOConsoleReader.promptRange("Hello " + username + ", How much would you like to bet?\n" +
                                "Please choose a number ", 0, account.getBalance());
                //will need to get right method
                Bet bet = promptForBet();
                bet.place();

                System.out.print("Spinning . . . ");
                WheelSpin.SpinResult spinResult = myWheel.spin();
                System.out.println(String.format("Dropped into %s", spinResult));
                if (bet.isMade(spinResult)) {
                    System.out.println("Congrats " + username +  "! You've won!");
                   account.addBalance(bet.payout(amount));



                } else {
                    System.out.println("Sorry, " + username +  " you have lost");
                    account.deductBalance(amount);

                }

                 String input = IOConsoleReader.promptString("This round of game has finished, would you like to play again? (yes or no)");
                 if (input.equalsIgnoreCase("no")) {
                     System.out.println("(( Your current balance is " + player.getAccount().getBalance() + " tokens. ))\n\n");
                     break;
                 }

            }

        }


    }

    private Bet promptForBet() {
        System.out.println("You can make any of these bets:");
        for (int k = 0; k < myPossibleBets.length; k++) {
            System.out.println(String.format("%d) %s", (k + 1), myPossibleBets[k]));
        }

        int response = IOConsoleReader.promptRange("Please make a choice", 1, myPossibleBets.length);
        return myPossibleBets[response - 1];


    }





    @Override
    public void payout(Account account, int payoutAmount) {

    }

    @Override
    public void beginGame() {

    }

    public void printSleepyBannerLineByLine(String message, int milliseconds) {
        String[] stringArray = message.split("\n");
        int len = stringArray.length;
        for (int i = 0; i < len; i++) {
            Sleep.sleep(milliseconds);
            System.out.print(stringArray[i]);
            if (i < len - 1) System.out.print("\n");
        }
    }

    @Override
    public String printInstructions() {
        printSleepyBannerLineByLine(""" 
                                                             ============ Welcome to!============
                                                                                                                                                 \s
                8 888888888o.      ,o888888o.     8 8888      88 8 8888         8 8888888888 8888888 8888888888 8888888 8888888888 8 8888888888  \s
                8 8888    `88.  . 8888     `88.   8 8888      88 8 8888         8 8888             8 8888             8 8888       8 8888        \s
                8 8888     `88 ,8 8888       `8b  8 8888      88 8 8888         8 8888             8 8888             8 8888       8 8888        \s
                8 8888     ,88 88 8888        `8b 8 8888      88 8 8888         8 8888             8 8888             8 8888       8 8888        \s
                8 8888.   ,88' 88 8888         88 8 8888      88 8 8888         8 888888888888     8 8888             8 8888       8 888888888888\s
                8 888888888P'  88 8888         88 8 8888      88 8 8888         8 8888             8 8888             8 8888       8 8888        \s
                8 8888`8b      88 8888        ,8P 8 8888      88 8 8888         8 8888             8 8888             8 8888       8 8888        \s
                8 8888 `8b.    `8 8888       ,8P  ` 8888     ,8P 8 8888         8 8888             8 8888             8 8888       8 8888        \s
                8 8888   `8b.   ` 8888     ,88'     8888   ,d8P  8 8888         8 8888             8 8888             8 8888       8 8888        \s
                8 8888     `88.    `8888888P'        `Y88888P'   8 888888888888 8 888888888888     8 8888             8 8888       8 888888888888\s
                                
                                
                                
                                                 ========================== Instructions: ==========================
                                                 1. Choose your wager
                                                 2. Select type of bet
                                                 3. - Red or Black
                                                 4. - Odd or Even
                                                 5. - Three in a row
                                
                                
                """, 200);
        return null;
    }


}
