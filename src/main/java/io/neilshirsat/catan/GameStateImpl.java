package io.neilshirsat.catan;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Implementation for the Game State
 */
//TODO: TRADE WITH THE BANK
public class GameStateImpl implements GameState {

    private int turn;

    private Map<Integer, Player> players;

    private TreeMap<ResourceType,Integer> resourceTypeDeck;

    private TreeMap<DevelopmentCards, Integer> developmentCardsDeck;

    private enum Stage {
        SETUP,
        STAGE_1,
        STAGE_2,
        STAGE_3,
        STAGE_4;


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
        /*
        get changedRobber from user interface

        NodeImpl.changeRobber(int NodeId);

         */
    }



    public void passDice() {
        turn++;
        if (turn>Player.amountPlayers) {
            turn = 1;
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
            case 4 -> Stage.STAGE_4;
            default -> null;
        };
    }

    public TreeMap<DevelopmentCards,Integer> setDevelopmentCardsDeck() {

        return developmentCardsDeck;
    }

    public TreeMap<ResourceType, Integer> setResourceTypeDeck() {

        return resourceTypeDeck;
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
