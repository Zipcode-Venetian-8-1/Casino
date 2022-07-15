package com.github.zipcodewilmington.casino.games.TicTacToe;
import com.github.zipcodewilmington.casino.GameInterface;

import com.github.zipcodewilmington.casino.Player;


import java.util.*;

/**
 * Created by leon on 7/21/2020.
 */
public class TicTacToe implements GameInterface{
    static ArrayList<String> playerOnePositions = new ArrayList<String>();
    static ArrayList<String> playerTwoPositions = new ArrayList<String>();

    @Override
    public void beginGame() {}

    public static void main (String[] args){

            Scanner scanner = new Scanner(System.in);
            int attempts = 0;
            String position= "5";
            //String[][] userInterface = {{" ", " ", " "},{" ", " ", " "},{" ", " ", " "}};
            String[][] demo = {{"a", "b", "c"}, {"d", "e", "f"}, {"g", "h", "i"}};
            displayUI(demo);
            gamePlay(attempts);
            declareWinner();
            //System.out.println(result);
        }



    private static boolean isValidPlay (String[][]userInterface, String userInput){
        //Scanner scanner = new Scanner(System.in);
        //String userInput = scanner.nextLine();
          switch(userInput) {
              case "a":
                  return (Objects.equals(userInterface[0][0], " "));
              case "b":
                  return
                          (Objects.equals(userInterface[0][1], " "));
              case "c":
                  return (Objects.equals(userInterface[0][2], " "));
              case "d":
                  return
                          (Objects.equals(userInterface[1][0], " "));
              case "e":
                  return (Objects.equals(userInterface[1][1], " "));
              case "f":
                  return
                          (Objects.equals(userInterface[1][2], " "));
              case "g":
                  return (Objects.equals(userInterface[2][0], " "));
              case "h":
                  return
                          (Objects.equals(userInterface[2][1], " "));
              case "i":
                  return
                          (Objects.equals(userInterface[2][2], " "));
              default:
                  return false;
          }
    }
    private static void gamePlay(int attempts) {
       while (true) {
            String[][] userInterface = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};

            userPlay(userInterface);
            displayUI(userInterface);
            userPlay2(userInterface);
            displayUI(userInterface);

            userPlay(userInterface);
            displayUI(userInterface);
            userPlay2(userInterface);
            displayUI(userInterface);

            userPlay(userInterface);
            displayUI(userInterface);
            userPlay2(userInterface);
            displayUI(userInterface);

            userPlay(userInterface);
            displayUI(userInterface);
            userPlay2(userInterface);
            displayUI(userInterface);

            userPlay(userInterface);
            displayUI(userInterface);
            userPlay2(userInterface);
            displayUI(userInterface);
            attempts++;
            declareWinner();
        }

    }

    public static String declareWinner(){

        List<String> firstRow = Arrays.asList("a", "b", "c");
        List<String> secondRow = Arrays.asList("d", "e", "f");
        List<String> thirdRow = Arrays.asList("g", "h", "i");
        List<String> firstCol = Arrays.asList("a", "b", "c");
        List<String> secondCol = Arrays.asList("d", "e", "f");
        List<String> thirdCol = Arrays.asList("g", "h", "i");
        List<String> leftAcr = Arrays.asList("a", "e", "i");
        List<String> rightAcr = Arrays.asList("c", "e", "g");


        List<List> winningMove = new ArrayList<List>();
        winningMove.add(firstRow);
        winningMove.add(secondRow);
        winningMove.add(thirdRow);
        winningMove.add(firstCol);
        winningMove.add(secondCol);
        winningMove.add(thirdCol);
        winningMove.add(leftAcr);
        winningMove.add(rightAcr);

        for(List l : winningMove){
            if(playerOnePositions.containsAll(l)){
                return "Congratulations Player1!";
            }
            else if(playerTwoPositions.contains(l)){
                return "Congratulations Player2";

            }
            else if(playerOnePositions.size() + playerTwoPositions.size() == 9){
                return "Game tied";
            }
        }
        return "";
    }

    private static void userPlay(String[][] userInterface) {
        Scanner scanner = new Scanner (System.in);
        String userInput = scanner.nextLine();
        System.out.println("Choose a position on the board. Options are 'a' through 'i'.");
        if(isValidPlay(userInterface, userInput)) {
            playerMove(userInterface, userInput);
            playerOnePositions.add(userInput);
        }
    }
    private static void userPlay2(String[][] userInterface) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        System.out.println("Choose a position on the board. Options are 'a' through 'i'.");
        if(isValidPlay(userInterface, userInput)) {
            playerMove2(userInterface, userInput);
            playerTwoPositions.add(userInput);
        }

    }

    private static void playerMove(String[][] userInterface, String userInput) {
        if (Objects.equals(userInput, "a")) {
            userInterface[0][0] = "X";
        } else if (Objects.equals(userInput, "b")) {
            userInterface[0][1] = "X";
        } else if (Objects.equals(userInput, "c")) {
            userInterface[0][2] = "X";
        } else if (Objects.equals(userInput, "d")) {
            userInterface[1][0] = "X";
        } else if (Objects.equals(userInput, "e")) {
            userInterface[1][1] = "X";
        } else if (Objects.equals(userInput, "f")) {
            userInterface[1][2] = "X";
        } else if (Objects.equals(userInput, "g")) {
            userInterface[2][0] = "X";
        } else if (Objects.equals(userInput, "h")) {
            userInterface[2][1] = "X";
        } else if (Objects.equals(userInput, "i")) {
            userInterface[2][2] = "X";
        } else {
            System.out.println("Invalid input. Try again.");
        }playerOnePositions.add(userInput);
    }



    private static void playerMove2(String[][] userInterface, String userInput) {
        if (Objects.equals(userInput, "a")){
            userInterface[0][0] = "O";
        } else if (Objects.equals(userInput, "b")){
            userInterface[0][1] = "O";
        }else if (Objects.equals(userInput, "c")){
            userInterface[0][2] = "O";
        }else if (Objects.equals(userInput, "d")){
            userInterface[1][0] = "O";
        }else if (Objects.equals(userInput, "e")){
            userInterface[1][1] = "O";
        }else if (Objects.equals(userInput, "f")){
            userInterface[1][2] = "O";
        }else if (Objects.equals(userInput, "g")){
            userInterface[2][0] = "O";
        }else if (Objects.equals(userInput, "h")){
            userInterface[2][1] = "O";
        }else if (Objects.equals(userInput, "i")){
            userInterface[2][2] = "O";
        } else { System.out.println("Invalid input. Try again.");

        }playerTwoPositions.add(userInput);
        declareWinner();
    }

    private static void displayUI(String[][] userInterface) {
        System.out.println(userInterface[0][0] + " | " + userInterface[0][1] + " | " + userInterface[0][2]);
        System.out.println("- + - + -");
        System.out.println(userInterface[1][0] + " | " + userInterface[1][1] + " | " + userInterface[1][2]);
        System.out.println("- + - + -");
        System.out.println(userInterface[2][0] + " | " + userInterface[2][1] + " | " + userInterface[2][2]);
        System.out.println("Choose a position on the board. Options are 'a' through 'i'.");
    }




    @Override
    public String printInstructions() {
        return null;
    }
}
