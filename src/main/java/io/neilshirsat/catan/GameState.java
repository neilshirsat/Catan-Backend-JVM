package io.neilshirsat.catan;

import java.util.List;

public interface GameState {

    int rollDice();

    void handleDiceRoll(int dice);

    void setGameModeTrade();

    void proposeTrade(List get, List give);

    void verifyTrade(String passcode);

    void passDice();

    void buildPorts();
}
