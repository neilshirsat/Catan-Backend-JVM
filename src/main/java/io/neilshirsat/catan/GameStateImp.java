package io.neilshirsat.catan;

import java.util.List;
import java.util.Random;

/**
 * The Implementation for the Game State
 */
public class GameStateImp implements GameState {

    @Override
    public void distributeDiceCards() {

    }

    @Override
    public int rollDice() {
        int die1, die2;
        die1 = (int) ((Math.random() * 5)+1);
        die2 = (int) ((Math.random() * 5)+1);
        return die1 + die2;
    }

    @Override
    public void placeInitialSettlement(int node) {

    }

    @Override
    public void placeInitialRoads(int edge) {

    }

    @Override
    public boolean canPlaceRoad(int edge) {
        return false;
    }

    @Override
    public boolean canPlaceSettlement(int edge) {
        return false;
    }

    @Override
    public boolean internalValidateEdgeRule(int node, int edge) {
        return false;
    }

    @Override
    public boolean internalValidateRoadConnectivity(int edge) {
        return false;
    }

    @Override
    public boolean internalValidateSettlementConnectivityWithRoad(int edge) {
        return false;
    }

    @Override
    public void internalIncrementTurnNumberOfStageOne() {
            int playerNum = 1;
            while(playerNum!=5) {
                playerNum++;
            }
            internalIncrementTurnNumberOfStageTwo();
    }

    @Override
    public void internalGoToStageTwo() {

    }

    @Override
    public void internalIncrementTurnNumberOfStageTwo() {
        int playerNum = 4;
        while(playerNum!=0){
            playerNum--;
        }
        internalIncrementTurnNumberOfStageOne();
    }

    @Override
    public void internalIncrementTurnCountRegularGame() {

    }

    @Override
    public void handleDiceRoll(int dice) {

    }

    @Override
    public void internalDistributeCardsOfRolledDice() {

    }

    @Override
    public void internalShouldDistributeCardsOfTile(int node) {

    }

    @Override
    public boolean internalGetCardsToDistribute(int node) {
        return false;
    }

    @Override
    public void internalGiveCardsToPlayer(int node) {

    }

    @Override
    public void internalHandelDiceRoll7() {

    }

    @Override
    public void internalSetGameStateToMoveRobber() {

    }

    @Override
    public void internalSetGameStateToNormal() {

    }

    @Override
    public void setRobberPosition(int node) {

    }

    @Override
    public void internalSetRobberPositionToFalseOfPreviousTile() {

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
    public void internalTradeCards() {

    }

    @Override
    public void internalRemoveCardsFromDeck() {

    }

    @Override
    public void internalAddCardsToDeck() {

    }

    @Override
    public void buildSettlement(int vert) {

    }

    @Override
    public void internalCheckIfCanBuildSettlement() {

    }

    @Override
    public boolean buildCity() {
        return false;
    }

    @Override
    public void internalCheckIfCanBuildCity() {

    }

    @Override
    public boolean getVictoryCard() {
        return false;
    }

    @Override
    public void internalCheckIfCanGetVictoryCard() {

    }

    @Override
    public boolean internalTransferMaterials() {
        return false;
    }

    @Override
    public void passDice() {

    }

    @Override
    public void internalIncrementTurn() {

    }

    @Override
    public void internalCheckForWinConditions() {

    }

    @Override
    public void buildPorts() {

    }
}
