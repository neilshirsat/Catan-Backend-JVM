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
                //p.discardCard((p.getAmountResourceCards()-1)/2);
                /*
                get discarded cards from user interface
                //TODO NEED CONFIRMATION

                 */
            }
        }
        /*
        get changedRobber from user interface

        NodeImpl.changeRobber(int NodeId);

         */
    }

    //TODO TAKE INTO ACCOUNT WHAT STAGE IS GOING ON
    public static void passDice() {
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

    public static class currentTrade {
        public static int amountTrades = 0;
        private final Map<ResourceType, Integer> tradeOutgoing;
        private final Map<ResourceType, Integer> tradeIngoing;
        private final int tradeId;

        public Player[] getTargetPlayers() {
            return targetPlayers;
        }

        private final Player[] targetPlayers;

        public Map<ResourceType, Integer> getTradeOutgoing() {
            return tradeOutgoing;
        }

        public Map<ResourceType, Integer> getTradeIngoing() {
            return tradeIngoing;
        }

        public int getTradeId() {
            return tradeId;
        }

        public static int getAmountTrades() {
            return amountTrades;
        }

        currentTrade(
                Map<ResourceType, Integer> tradeOutgoing,
                Map<ResourceType, Integer> tradeIngoing,
                int tradeId,
                Player[] targetPlayers) {
            this.tradeOutgoing = tradeOutgoing;
            this.tradeIngoing = tradeIngoing;
            this.tradeId = tradeId;
            this.targetPlayers = targetPlayers;
            amountTrades++;
        }


    }

    public void resetGame(){
        for (Player p : Player.getAllPlayers()){
            p.setPasscode(null);
            p.setPlayerName(null);
            Player.resetLargestArmy();
            //p.setDeck(null,null);
            p.setVictoryPoints(0);
            p.setAmountRoads(15);
            p.setAmountCities(4);
            p.setAmountSettlements(5);
            // p.setSpecialCards(null,null);
            // not sure how to go back to start screen
        }
    }
}
