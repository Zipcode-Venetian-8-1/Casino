package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.AccountManager;
import com.github.zipcodewilmington.casino.games.TicTacToe.TicTacToe;
import com.github.zipcodewilmington.casino.games.TicTacToe.TicTacToePlayer;
import com.github.zipcodewilmington.casino.games.cardgames.ThreeCardPokerGame;
import com.github.zipcodewilmington.casino.games.cardgames.ThreeCardPokerPlayer;
import com.github.zipcodewilmington.casino.games.dicegame.HighLowDice;
import com.github.zipcodewilmington.casino.games.dicegame.HighLowDicePlayer;
import com.github.zipcodewilmington.casino.games.roulette.Roulette;
import com.github.zipcodewilmington.casino.games.roulette.RoulettePlayer;
import com.github.zipcodewilmington.casino.games.slots.SlotsGame;
import com.github.zipcodewilmington.casino.games.slots.SlotsPlayer;
import com.github.zipcodewilmington.utils.*;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;

public class Casino {
    private final IOConsole console = new IOConsole(AnsiColor.CYAN);

    public void run() throws IOException {
        String dashBoardInput;
        AccountManager accountManager = new AccountManager();
        HashSet<Account> loggedInAccounts = new HashSet<>();
        console.print("\n\n\n\n\n");
        printVenetianBanner();
        do {
            dashBoardInput = getDashboardInput().toUpperCase();

            switch (dashBoardInput) {

                case "CREATE ACCOUNT":
                case "1":
                case "CREATE":
                case "C": {
                    console.println("Welcome to the account-creation screen.");
                    String accountName = console.getStringInput("Enter your account name:");
                    String accountPassword = console.getStringInput("Enter your account password:");
                    Integer startingBalance = console.getIntegerInput("Enter the amount of tokens would you like to add to your account:");
                    if (accountManager.getAccountUsernames().contains(accountName)) {
                        System.out.print("This username already exists");
                        for (int i = 0; i < 4; i++) {
                            Sleep.sleep(500);
                            console.print(".");
                        }
                    } else {
                        accountManager.createAccount(accountName, accountPassword, startingBalance);
                        accountManager.updateAccounts();
                    }
                    break;
                }

                case "SELECT GAME":
                case "2":
                    String gameSelectionInput = getGameSelectionInput().toUpperCase();
                    boolean loginMore = false;
                    switch (gameSelectionInput) {

                        case "THREE CARD POKER":
                        case "2":
                        case "HIGH LOW DICE":
                        case "3":
                            while (true) {
                                while (loggedInAccounts.size() == 0 || loginMore) {
                                    Account account = loginPrompt(accountManager);
                                    if (account != null) {
                                        loggedInAccounts.add(account);
                                        loginMore = false;
                                    } else {
                                        noAccountFound();
                                        break;
                                    }
                                }
                                int loginMoreOrPlay = console.getIntegerInput(
                                        "(1) Login another user  (2) Begin Play  (3) Return to Main Lobby");
                                if (loginMoreOrPlay == 2) {
                                    if (gameSelectionInput.equalsIgnoreCase("THREE CARD POKER") ||
                                    gameSelectionInput.equals("2")) {
                                        HashSet<ThreeCardPokerPlayer> threeCardPlayers = new HashSet<>();
                                        for (Account account : loggedInAccounts) {
                                            threeCardPlayers.add(new ThreeCardPokerPlayer(account));
                                        }
                                        new ThreeCardPokerGame(threeCardPlayers).beginGame();
                                    } else {
                                        HashSet<HighLowDicePlayer> dicePlayers = new HashSet<>();
                                        for (Account account : loggedInAccounts) {
                                            dicePlayers.add(new HighLowDicePlayer(account));
                                        }
                                        new HighLowDice(dicePlayers).beginGame();
                                    }
                                    accountManager.updateAccounts();
                                    break;
                                } else if (loginMoreOrPlay == 3) { break; }
                                else if (loginMoreOrPlay == 1) { loginMore = true; }
                            }
                            break;

                        case "TIC TAC TOE":
                        case "4":
                        case "ROULETTE":
                        case "5":
                        case "SLOTS":
                        case "1":
                        {
                            if (loggedInAccounts.size() > 1) System.out.println("Too many accounts returning to lobby...");
                            else {
                                Account account = null;
                                if (loggedInAccounts.size() == 0) {
                                    account = loginPrompt(accountManager);
                                    if (account == null) noAccountFound();
                                } else { // has to be one account logged in already
                                    for (Account loggedAccount : loggedInAccounts) account = loggedAccount;
                                }
                                if (account != null) {
                                    loggedInAccounts.add(account);
                                    if (gameSelectionInput.equalsIgnoreCase("SLOTS") ||
                                            gameSelectionInput.equals("1")) {
                                        new SlotsGame(new HashSet<>(Collections.singleton(new SlotsPlayer(account)))).beginGame();
                                    } else if (gameSelectionInput.equalsIgnoreCase("TIC TAC TOE") ||
                                    gameSelectionInput.equals("4")) {
                                        new TicTacToe(new HashSet<>(Collections.singleton(new TicTacToePlayer(account)))).beginGame();
                                    } else {
                                        new Roulette(new RoulettePlayer(account)).play(account);
                                    }
                                    accountManager.updateAccounts();
                                }
                            }
                            break;
                        }

                        default:
                            String errorMessage = "[ %s ] is an invalid game selection";
                            System.out.format(errorMessage, gameSelectionInput);
                    }
                    printVenetianBanner();
                    break;

                case "CASHIER":
                case "3":

                    Account cashierAccount = loginPrompt(accountManager);
                    if (cashierAccount == null) {
                        noAccountFound();
                        break;
                    }

                    String cashierInput;
                    do {
                        cashierInput = console.getStringInput("(1) CHECK BALANCE  (2) ADD BALANCE  (3) WITHDRAW BALANCE  (4) BACK TO LOBBY");
                        if (cashierInput.equalsIgnoreCase("CHECK BALANCE") ||
                                cashierInput.equals("1")) {
                            console.println("Your balance is " + cashierAccount.getBalance() + " tokens.");
                        } else if (cashierInput.equalsIgnoreCase("ADD BALANCE") ||
                                cashierInput.equals("2")) {
                            Integer balanceToAdd = console.getIntegerInput("How many tokens would you like to deposit?");
                            if (balanceToAdd > 0) {
                                cashierAccount.addBalance(balanceToAdd);
                                console.println(balanceToAdd + " tokens have been deposited.\n" +
                                        "You now have " + cashierAccount.getBalance() + " tokens.");
                            } else {
                                console.println("Please enter a positive amount of tokens to deposit.");
                            }
                        } else if (cashierInput.equalsIgnoreCase("WITHDRAW BALANCE") ||
                                cashierInput.equals("3")) {
                            Integer balanceToWithdraw = console.getIntegerInput("How many tokens would you like to withdraw?");
                            if (balanceToWithdraw <= cashierAccount.getBalance()) {
                                cashierAccount.deductBalance(balanceToWithdraw);
                                console.println(balanceToWithdraw + " tokens have been withdrawn.\n" +
                                        "You now have " + cashierAccount.getBalance() + " tokens.");
                            } else {
                                console.println("You do not have that many tokens in your account to withdraw...");
                            }
                        } else {
                            break;
                        }
                    } while (cashierInput.equalsIgnoreCase("CHECK BALANCE") || cashierInput.equalsIgnoreCase("ADD BALANCE") ||
                            cashierInput.equalsIgnoreCase("WITHDRAW BALANCE") || cashierInput.equals("1") ||
                            cashierInput.equals("2") || cashierInput.equals("3"));
                    accountManager.updateAccounts();
                    break;

                case "LOGOUT ACCOUNT":
                case "LOGOUT":
                case "L":
                case "4":
                    if (loggedInAccounts.size() == 1) {
                        loggedInAccounts.clear();
                    } else if (loggedInAccounts.size() > 1) {
                        String usernameToLogout = console.getStringInput("Username to logout:");
                        String passwordToLogout = console.getStringInput("Password to logout:");
                        loggedInAccounts.remove(accountManager.getAccount(usernameToLogout, passwordToLogout));
                    } else {
                        System.out.print("No one is logged in");
                        for (int i = 0; i < 4; i++) {
                            Sleep.sleep(500);
                            console.print(".");
                        }
                    }
                    break;

            }
        } while (!"QUIT".equalsIgnoreCase(dashBoardInput) && !"5".equals(dashBoardInput));
    }

    private String getDashboardInput() {
        return console.getStringInput(
         "\nSelect an option number:" +
                "\n(1) Create New Account  (2) Select Game  (3) Cashier  (4) Logout Account  (5) Quit");
    }

    private String getGameSelectionInput() {
        return console.getStringInput("From here, you can select any of the following games:" +
                "\n(1) SLOTS  (2) THREE CARD POKER  (3) HIGH LOW DICE  (4) TIC TAC TOE  (5) ROULETTE");
    }

    private Account loginPrompt(AccountManager accountManager) {
        String accountName = console.getStringInput("Enter your account name:");
        String accountPassword = console.getStringInput("Enter your account password:");
        return accountManager.getAccount(accountName, accountPassword);
    }

    private void noAccountFound() {
        console.println("No account found with that name and password.\n");
    }

    public void printSleepyBannerLineByLine(String message, int milliseconds) {
        String[] stringArray = message.split("\n");
        int len = stringArray.length;
        for (int i = 0; i < len; i++) {
            Sleep.sleep(milliseconds);
            console.print(stringArray[i]);
            if (i < len - 1) console.print("\n");
        }
    }

    private void printVenetianBanner() {
        printSleepyBannerLineByLine(
             "\noooooo     oooo oooooooooooo ooooo      ooo oooooooooooo ooooooooooooo ooooo       .o.       ooooo      ooo \n" +
                        " `888.     .8'  `888'     `8 `888b.     `8' `888'     `8 8'   888   `8 `888'      .888.      `888b.     `8' \n" +
                        "  `888.   .8'    888          8 `88b.    8   888              888       888      .8 888.      8 `88b.    8  \n" +
                        "   `888. .8'     888oooo8     8   `88b.  8   888oooo8         888       888     .8' `888.     8   `88b.  8  \n" +
                        "    `888.8'      888          8     `88b.8   888              888       888    .88ooo8888.    8     `88b.8  \n" +
                        "     `888'       888       o  8       `888   888       o      888       888   .8'     `888.   8       `888  \n" +
                        "      `8'       o888ooooood8 o8o        `8  o888ooooood8     o888o     o888o o88o     o8888o o8o        `8  \n" +
                        "                                                                                                            \n" +
                        "                                                                                                            \n", 225);
    }
}
