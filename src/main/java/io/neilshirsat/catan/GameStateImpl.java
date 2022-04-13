package io.neilshirsat.catan;

import java.util.List;
import java.util.Map;

/**
 * The Implementation for the Game State
 */
public class GameStateImpl implements GameState {

    private int turnNumber;

    private int turn;

    private Map<Player, Integer> players;

    private enum Stage {
        SETUP,
        STAGE_1,
        STAGE_2,
        STAGE_3;
    }

    private Stage stage = Stage.SETUP;

    private enum ActionStage {
        NORMAL,
        SPECIAL_7,
    }

    private ActionStage actionStage;

    public static List<Integer> diceProbabilityList =
            List.of(
                    7,7,7,7,7,7,
                    6,6,6,6,6,
                    8,8,8,8,8,
                    5,5,5,5,
                    9,9,9,9,
                    4,4,4,
                    10,10,10,
                    3,3,
                    11,11,
                    2,
                    12
            );

    /**
     * Rolls the Dice and Returns the Rolled Dice Number
     */
    public int rollDice() {
        return diceProbabilityList.get((int)(Math.random() * 16));
    }

    /**
     * Handle the Dice Roll By Giving Resource Cards Out Based on the
     * Amount of Resource Cards that the Dice Has
     *
     * @param dice
     */
    public void handleDiceRoll(int dice) {
        if (dice != 7) {
            actionStage = ActionStage.NORMAL;
           NodeImpl.incrementPlayers(NodeImpl.getNodesWithDice(dice));
        }
        else actionStage = ActionStage.SPECIAL_7;
        /*
        get changedRobber from user interface

        NodeImpl.changeRobber(int NodeId);

         */
    }

    public Player verifyTrade(String passcode) {
        for (Map.Entry<Player, Integer> k : players.entrySet()) {
            if (k.getKey().getPasscode().equals(passcode)) {
                return k.getKey();
                //trade?? (tradeCards)
            }
        }
        return null;
    }

    public void passDice() {

    }

    public void incrementVictoryPoints() {
        for (Map.Entry<Player, Integer> k : players.entrySet()) {
            players.put(k.getKey(), k.getKey().getVictoryPoints());
        }
    }

    public Player checkPlayerWin() {
        for (Map.Entry<Player, Integer> k: players.entrySet()) {
            if (k.getValue()>=10)
                return k.getKey();
        }
        return null;
    }


}
