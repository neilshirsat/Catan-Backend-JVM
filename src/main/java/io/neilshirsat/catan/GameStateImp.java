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
    public void distributeDiceCards() {

    }

    @Override
    public int rollDice() {
        return 0;
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

    }

    @Override
    public void internalGoToStageTwo() {

    }

    @Override
    public void internalIncrementTurnNumberOfStageTwo() {

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
