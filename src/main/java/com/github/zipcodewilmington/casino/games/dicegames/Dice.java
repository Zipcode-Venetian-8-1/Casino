package com.github.zipcodewilmington.casino.games.dicegames;

import java.util.Random;

public class Dice {
    private int value;
    private Random rand;
    private int numDice;

    public Dice(int numDice) {
        this.rand = new Random();
        this.numDice = numDice;
    }

    public int tossAndSum() {
        this.value = 0;
        for (int i = 0; i < this.numDice; i++) {
            this.value += rand.nextInt(6) + 1;
        }
        return this.value;
    }


    public int getValue() {
        return this.value;
    }
//    private int value;
//    private Random rand;
//
//    public Dice() {
//        this.rand = new Random();
//    }
//
//    public int roll() {
// //       this.value = rand.nextInt(1,7);
//        return getValue();
//    }
//
//
//    public int getValue() {
//        return this.value;
//    }

}
