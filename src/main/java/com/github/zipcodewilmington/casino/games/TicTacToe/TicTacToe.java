package com.github.zipcodewilmington.casino.games.TicTacToe;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.Player;



import java.util.Objects;
import java.util.Scanner;

/**
 * Created by leon on 7/21/2020.
 */
public class TicTacToe implements GameInterface {
    public static void main (String[] args){
        int attempts = 0;
        String position= "5";
        String[][] userInterface = {{" ", " ", " "},{" ", " ", " "},{" ", " ", " "}};
        String[][] demo = {{"a", "b", "c"}, {"d", "e", "f"}, {"g", "h", "i"}};
        displayUI(demo);
        gamePlay(attempts);



    }



    private static boolean isValidPlay (String[][]userInterface, String position){
          switch(position) {
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
                  return true;
          }
    }
    private static void gamePlay(int attempts) {
       // while (attempts <= 5) {
            String[][] userInterface = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};

            userPlay(userInterface); //create while loop that runs nine times???? Nope.
            displayUI(userInterface);//create while loop that runs five times?
            userPlay2(userInterface);
            displayUI(userInterface);

            userPlay(userInterface); //
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
        }
   // }

    private static void userPlay(String[][] userInterface) {
        Scanner scanner = new Scanner(System.in);
//       while (true){
        String userInput = scanner.nextLine();
            System.out.println("Choose a position on the board. Options are 'a' through 'i'.");
        playerMove(userInterface, userInput);
    }
    private static void userPlay2(String[][] userInterface) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a position on the board. Options are 'a' through 'i'.");
        String userInput = scanner.nextLine();
        playerMove2(userInterface, userInput);
        // while (true){if (isValidPlay(userInterface, userInput)){
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
        }
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
        }
    }

    private static void displayUI(String[][] userInterface) {
        System.out.println(userInterface[0][0] + " | " + userInterface[0][1] + " | " + userInterface[0][2]);
        System.out.println("- + - + -");
        System.out.println(userInterface[1][0] + " | " + userInterface[1][1] + " | " + userInterface[1][2]);
        System.out.println("- + - + -");
        System.out.println(userInterface[2][0] + " | " + userInterface[2][1] + " | " + userInterface[2][2]);
    }



    @Override
    public void beginGame() {

    }

    @Override
    public String printInstructions() {
        return null;
    }

    @Override
    public Player decideWinner() {

        return null;
    }
}