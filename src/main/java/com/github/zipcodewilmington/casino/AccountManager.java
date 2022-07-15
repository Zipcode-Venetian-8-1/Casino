package com.github.zipcodewilmington.casino;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class AccountManager {

    private HashMap<String, Account> accounts;
    private final Path file = Paths.get("accounts.txt");

    public AccountManager() throws IOException {
        this.accounts = new HashMap<>();
        BufferedReader reader = Files.newBufferedReader(this.file);
        String line;
        while ((line = reader.readLine()) != null) {
            String[] acctData = line.split(",");
            Account acct = new Account(acctData[0], acctData[1], Integer.parseInt(acctData[2]));
            this.accounts.put(acct.getUserName(), acct);
        }
    }

    public Account getAccount(String username, String password) {
        if (this.accounts.containsKey(username)) {
            Account acct = this.accounts.get(username);
            if (acct.getPassword().equals(password)) {
                return acct;
            }
        }
        return null;
    }

    public Account createAccount(String username, String password) {
        // Account already exists
        if (this.accounts.containsKey(username)) {
            return null;
        } else {
            Account newAcct = new Account(username, password, 1000);
            this.accounts.put(username, newAcct);
            return newAcct;
        }
    }

    public Account createAccount(String username, String password, int startBalance) {
        // Account already exists
        if (this.accounts.containsKey(username)) {
            return null;
        } else {
            Account newAcct = new Account(username, password, startBalance);
            this.accounts.put(username, newAcct);
            return newAcct;
        }
    }

    public void registerAccount(Account account) {

    }

    public void updateAccounts() throws IOException {
        String data = "";
        for (HashMap.Entry<String, Account> entry : this.accounts.entrySet()) {
            data += entry.getValue().getUserName() + "," + entry.getValue().getPassword() + "," + entry.getValue().getBalance() + "\n";
        }
        byte[] accountsData = data.getBytes();
        Files.write(this.file, accountsData);
    }
}
