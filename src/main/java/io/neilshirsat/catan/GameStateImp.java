package io.neilshirsat.catan;

import java.util.List;
import java.util.Map;

/**
 * The Implementation for the Game State
 */
public class GameStateImp implements GameState {

    private int turnNumber;

    private int turn;

    private Map<Integer, Player> players;

    private enum Stage {
        STAGE_1,
        STAGE_2,
        STAGE_3;
    }

    private Stage stage;

    private enum ActionStage {
        NORMAL,
        SPECIAL_7,
    }

    private ActionStage actionStage;

    @Override
    public int rollDice() {
        return 0;
    }

    @Override
    public void handleDiceRoll(int dice) {

    }

    @Override
    public void setGameModeTrade() {

    }

    @Override
    public void proposeTrade(List get, List give) {

    }

    @Override
    public void verifyTrade(String passcode) {

    }

    @Override
    public void passDice() {

    }

    @Override
    public void buildPorts() {

    }

}
