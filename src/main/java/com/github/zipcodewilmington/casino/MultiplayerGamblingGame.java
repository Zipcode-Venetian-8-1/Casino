package com.github.zipcodewilmington.casino;

import java.util.HashSet;

public interface MultiplayerGamblingGame extends GamblingGameInterface{
    HashSet<Player> decideWinner(HashSet<Player> players);
}
