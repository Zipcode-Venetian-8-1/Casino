package com.github.zipcodewilmington.casino;

/**
 * Created by leon on 7/21/2020.
 * `ArcadeAccount` is registered for each user of the `Arcade`.
 * The `ArcadeAccount` is used to log into the system to select a `Game` to play.
 */
public class Account {
    private final String userName;
    private final String password;
    private int balance;

    public Account(String userName, String password, int balance) {
        this.userName = userName;
        this.password = password;
        this.balance = balance;
    }

    public void deductBalance(int amount) {
        this.balance = balance - amount;
    }

    public void addBalance(int amount) {
        this.balance = balance + amount;
    }

    public boolean checkBalance(int amount) {
        if (this.balance >= amount) return true;
        else return false;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String toString() {
        return this.userName + "," + this.password + "," + String.format("%.2f", this.balance) + "\n";
    }
}
