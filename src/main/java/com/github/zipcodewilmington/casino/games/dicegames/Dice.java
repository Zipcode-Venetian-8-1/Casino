package com.github.zipcodewilmington.casino.games.dicegames;

import java.util.Random;

public class Dice {

    private int value;
    private Random rand;

    public Dice() {
        this.rand = new Random();
    }

    public int roll() {
 //       this.value = rand.nextInt(1,7);
        return getValue();
    }

    public int getValue() {
        return this.value;
    }

}
