package com.github.zipcodewilmington.casino.games.TicTacToe;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.*;

/**
 * Created by leon on 7/21/2020.
 */
public class TicTacToe implements GameInterface {

    private final IOConsole console = new IOConsole(AnsiColor.BLUE);
    private  HashSet<TicTacToePlayer> players;

    @Override
    public String printInstructions() {
        return "============Welcome to Venetian TIC TAC TOE!============";
    }

    @Override
    public void beginGame() {
        // practice note
        while (true) {
            String[][] demo = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};

            String[][] userInterface = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};

            displayUI(demo);

            while (true) { // gameplay loop
                userPlay(userInterface);
                if (isRoundComplete(userInterface)) {
                    break;
                }
                displayUI(userInterface);

                computerPlay(userInterface);
                if (isRoundComplete(userInterface)) {
                    break;
                }
                displayUI(userInterface);
            }
            // prompt user to play again?
            Integer playAgainInput = console.getIntegerInput("(1) Play Again (2) Quit");
            if (playAgainInput == 2) {
                break;
            }
        }

    }
public TicTacToe(HashSet< TicTacToePlayer > TicTacToePlayer) {this.players = TicTacToePlayer;}


    public boolean isRoundComplete(String[][] userInterface) {

        if (declareWinner(userInterface, "X")) {

            displayUI(userInterface);
            System.out.println("Congrats player!");
            return true;
        }

        if (declareWinner(userInterface, "O")) {
            displayUI(userInterface);
            System.out.println("Sorry machine beats man!");
            return true;
        }

        for (int i = 0; i < userInterface.length; i++) {
            for (int j = 0; j < userInterface[i].length; j++) {
                if (userInterface[i][j] == " ") {
                    return false;
                }
            }
        }
        displayUI(userInterface);
        System.out.println("It's a tie!");
        return true;
    }


    public boolean declareWinner(String[][] board, String symbol) {
        if ((Objects.equals(board[0][0], symbol) && Objects.equals(board[0][1], symbol) && Objects.equals(board[0][2], symbol)) ||
                (Objects.equals(board[1][0], symbol) && Objects.equals(board[1][1], symbol) && Objects.equals(board[1][2], symbol)) ||
                (Objects.equals(board[2][0], symbol) && Objects.equals(board[2][1], symbol) && Objects.equals(board[2][2], symbol)) ||

                (Objects.equals(board[0][0], symbol) && Objects.equals(board[1][0], symbol) && Objects.equals(board[2][0], symbol)) ||
                (Objects.equals(board[0][1], symbol) && Objects.equals(board[1][1], symbol) && Objects.equals(board[2][1], symbol)) ||
                (Objects.equals(board[0][2], symbol) && Objects.equals(board[1][2], symbol) && Objects.equals(board[2][2], symbol)) ||

                (Objects.equals(board[0][0], symbol) && Objects.equals(board[1][1], symbol) && Objects.equals(board[2][2], symbol)) ||
                (Objects.equals(board[0][2], symbol) && Objects.equals(board[1][1], symbol) && Objects.equals(board[2][0], symbol)) ) {
            return true;
        }
        return false;
    }


    private void computerPlay(String[][] board) {
        Random rand = new Random();
        int computerMove;
        while (true) {
            computerMove = rand.nextInt(9) + 1;
            if (isValidPlay(board, Integer.toString(computerMove))) {
                break;
            }
        }
        System.out.println("Computer played " + computerMove);
        playerMove(board, Integer.toString(computerMove), "O");
    }
    private void userPlay (String[][] userInterface) {
        String userInput;
        while (true) {
            userInput = console.getStringInput("Where would you like to play? (1-9)");
            if (isValidPlay(userInterface, userInput)){
                break;
            } else {
                System.out.println(userInput + " is not a valid move.");
            }
        }
        playerMove(userInterface, userInput, "X");
    }

    private boolean isValidPlay (String[][] userInterface, String position) {
        switch(position) {
            case "1":
                return (Objects.equals(userInterface[0][0], " "));
            case "2":
                return (Objects.equals(userInterface[0][1], " "));
            case "3":
                return (Objects.equals(userInterface[0][2], " "));
            case "4":
                return (Objects.equals(userInterface[1][0], " "));
            case "5":
                return (Objects.equals(userInterface[1][1], " "));
            case "6":
                return (Objects.equals(userInterface[1][2], " "));
            case "7":
                return (Objects.equals(userInterface[2][0], " "));
            case "8":
                return (Objects.equals(userInterface[2][1], " "));
            case "9":
                return (Objects.equals(userInterface[2][2], " "));
            default:
                return false;
        }
    }
    private void playerMove(String[][] userInterface, String position, String symbol) {
        switch(position) {
            case "1":
                userInterface[0][0] = symbol;
                break;
            case "2":
                userInterface[0][1] = symbol;
                break;
            case "3":
                userInterface[0][2] = symbol;
                break;
            case "4":
                userInterface[1][0] = symbol;
                break;
            case "5":
                userInterface[1][1] = symbol;
                break;
            case "6":
                userInterface[1][2] = symbol;
                break;
            case "7":
                userInterface[2][0] = symbol;
                break;
            case "8":
                userInterface[2][1] = symbol;
                break;
            case "9":
                userInterface[2][2] = symbol;
                break;
            default:
                System.out.println("This is not a valid play.");
        }
    }

    private void displayUI(String[][] userInterface) {
        System.out.println(userInterface[0][0] + " | " +  userInterface[0][1] + " | " +  userInterface[0][2] );
        System.out.println("-  -  - - ");
        System.out.println(userInterface[1][0] + " | " +  userInterface[1][1] + " | " +  userInterface[1][2] );
        System.out.println("-  -  - - ");
        System.out.println(userInterface[2][0] + " | " +  userInterface[2][1] + " | " + userInterface[2][2] );
    }



}