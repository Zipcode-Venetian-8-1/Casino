package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.AccountManager;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.games.slots.SlotsGame;
import com.github.zipcodewilmington.casino.games.slots.SlotsPlayer;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.io.IOException;

/**
 * Created by leon on 7/21/2020.
 */
public class Casino {
    private final IOConsole console = new IOConsole(AnsiColor.BLUE);

    public void run() throws IOException {
        String arcadeDashBoardInput;
        AccountManager accountManager = new AccountManager();
       try {
           do {
               arcadeDashBoardInput = getArcadeDashboardInput();
               if ("select-game".equals(arcadeDashBoardInput)) {
                   String accountName = console.getStringInput("Enter your account name:");
                   String accountPassword = console.getStringInput("Enter your account password:");
                   Account account = accountManager.getAccount(accountName, accountPassword);
                   boolean isValidLogin = account != null;
                   if (isValidLogin) {
                       String gameSelectionInput = getGameSelectionInput().toUpperCase();
                       if (gameSelectionInput.equals("SLOTS")) {
                           play(new SlotsGame(), new SlotsPlayer());
                       }
//                    else if (gameSelectionInput.equals("BLACK JACK")) {
//                        play(new BlackJackGame(), new BlackJackPlayer());
//                    } else if (gameSelectionInput.equals("THREE CARD POKER")){
//                        play(new ThreeCardPokerGame(), new ThreeCardPokerPlayer());
//                    } else if (gameSelectionInput.equals("HI LO DICE")){
//                        play (new HiLoDiceGame(), new HiLoDicePlayer());
//                    } else if (gameSelectionInput.equals("ROULETTE")){
//                        play (new RouletteGame(), new RoulettePlayer());
//                    } else if (gameSelectionInput.equals("TIC TAC TOE")){
//                        play (new TicTacToeGame(), new TicTacToePlayer());
//                    }
                       else {
                           // TODO - implement better exception handling
                           String errorMessage = "[ %s ] is an invalid game selection";

                           throw new RuntimeException(String.format(errorMessage, gameSelectionInput));

                       }
                   } else {
                       // TODO - implement better exception handling
                       String errorMessage = "No account found with name of [ %s ] and password of [ %s ]";

                       throw new RuntimeException(String.format(errorMessage, accountPassword, accountName));

                   }
               } else if ("create-account".equals(arcadeDashBoardInput)) {
                   console.println("Welcome to the account-creation screen.");
                   String accountName = console.getStringInput("Enter your account name:");
                   String accountPassword = console.getStringInput("Enter your account password:");
                   Account newAccount = accountManager.createAccount(accountName, accountPassword);
                   accountManager.registerAccount(newAccount);
               }
           } while (!"logout".equals(arcadeDashBoardInput));
       } catch (RuntimeException e){System.out.println(e.getMessage());}
    }

    private String getArcadeDashboardInput() {
        return console.getStringInput("Welcome to the Arcade Dashboard!" +
                "\nFrom here, you can select any of the following options:" +
                "\n\t[ create-account ], [select-game" +
                "]");
    }

    private String getGameSelectionInput() {
        return console.getStringInput("Welcome to the Game Selection Dashboard!" +
                "\nFrom here, you can select any of the following options:" +
                "\n\t[ SLOTS ], [ THREE CARD POKER ], [ BLACK JACK ], [ ROULETTE ], [ HI LO DICE ], [ TIC TAC TOE ]");
    }

    private void play(Object gameObject, Object playerObject) {
        GameInterface game = (GameInterface)gameObject;
        //PlayerInterface player = (PlayerInterface)playerObject;
        //game.add(player);
        //game.run();
    }
}
