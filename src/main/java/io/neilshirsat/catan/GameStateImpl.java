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

    private int turn = 1;

    private Map<Integer, Player> players;

    enum Stage {
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
    }

    //TODO TAKE INTO ACCOUNT WHAT STAGE IS GOING ON
    public void passDice() {
        turn++;
        if (turn > Player.amountPlayers) {
            turn = 1;
        }
    }

    public void setStage(int stage) {
        switch (stage) {
            case 1 -> this.stage = Stage.STAGE_1; //roll dice
            case 2 -> this.stage = Stage.STAGE_2; //build/trade
            case 3 -> this.stage = Stage.STAGE_3; //move knight
        };
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return this.stage;
    }



    public Map.Entry<Integer, Player> checkPlayerWin() {
            for (Map.Entry<Integer, Player> k : players.entrySet()) {
                if (k.getKey() >= 10)
                    return k;}
                return null;
    }

    public void changePlayerName(String name, int playerId) {
        final Player player = Player.getPlayer(playerId);
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
