package io.neilshirsat.catan;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Implementation for the Game State
 */

public class GameStateImpl implements GameState {

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    private int turn;

    private Map<Integer, Player> players;

    private enum Stage {
        SETUP,

        //When the Players get to put down a road, settlement, or city
        STAGE_1,

        //When the Players get to trade (password)
        STAGE_2,

        //
        STAGE_3,

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
           NodeImpl.receiveCards(NodeImpl.getNodesWithDice(dice));
        }
        else actionStage = ActionStage.SPECIAL_7;
        for (Player p : Player.getAllPlayers()) {
            if (p.getAmountResourceCards()>7) {
                /*
                get discarded cards from user interface


                 */
            }
        }
        /*
        get changedRobber from user interface

        NodeImpl.changeRobber(int NodeId);

         */
    }

    //TODO TAKE INTO ACCOUNT WHAT STAGE IS GOING ON
    public void passDice() {
        turn++;
        if (turn>=Player.amountPlayers) {
            turn = 0;

        }
    }

    public void setStage() {
        stage = getStage(turn);
    }

    public Stage getStage(int turn) {
        return switch (turn) {
            case 1 -> Stage.STAGE_1;
            case 2 -> Stage.STAGE_2;
            case 3 -> Stage.STAGE_3;
            //case 4 -> Stage.STAGE_4;
            default -> null;
        };
    }

    public Map.Entry<Integer, Player> checkPlayerWin() {
            for (Map.Entry<Integer, Player> k : players.entrySet()) {
                if (k.getKey() >= 10)
                    return k;}
                return null;
    }

    public void changePlayerName(String name, int playerId) {
        final Player player = this.players.get(playerId);
        player.setPlayerName(name);
    }

    public void changePlayerPasscode(String password, int playerId) {
        final Player player = this.players.get(playerId);
        player.setPasscode(password);
    }
}
