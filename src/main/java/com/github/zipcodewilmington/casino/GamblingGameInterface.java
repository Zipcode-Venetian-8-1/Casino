package com.github.zipcodewilmington.casino;

public interface GamblingGameInterface extends GameInterface {
    void payout(Account account, int payoutAmount);
}
