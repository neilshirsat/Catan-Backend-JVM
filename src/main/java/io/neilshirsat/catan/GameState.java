package io.neilshirsat.catan;

import java.util.List;

public interface GameState {

    void distributeDiceCards();

    int rollDice();

    void placeInitialSettlement(int node);

    void placeInitialRoads(int edge);

    boolean canPlaceRoad(int edge);

    boolean canPlaceSettlement(int edge);

    boolean internalValidateEdgeRule(int node, int edge);

    boolean internalValidateRoadConnectivity(int edge);

    boolean internalValidateSettlementConnectivityWithRoad(int edge);

    void internalIncrementTurnNumberOfStageOne();

    void internalGoToStageTwo();

    void internalIncrementTurnNumberOfStageTwo();

    void internalIncrementTurnCountRegularGame();

    void handleDiceRoll(int dice);

    void internalDistributeCardsOfRolledDice();

    void internalShouldDistributeCardsOfTile(int node);

    boolean internalGetCardsToDistribute(int node);

    void internalGiveCardsToPlayer(int node);

    void internalHandelDiceRoll7();

    void internalSetGameStateToMoveRobber();

    void internalSetGameStateToNormal();

    void setRobberPosition(int node);

    void internalSetRobberPositionToFalseOfPreviousTile();

    void setGameModeTrade();

    void proposeTrade(List get, List give);

    void verifyTrade(String passcode);

    void internalTradeCards();

    void internalRemoveCardsFromDeck();

    void internalAddCardsToDeck();

    void buildSettlement(int vert);

    void internalCheckIfCanBuildSettlement();

    boolean buildCity();

    void internalCheckIfCanBuildCity();

    boolean getVictoryCard();

    void internalCheckIfCanGetVictoryCard();

    boolean internalTransferMaterials();

    void passDice();

    void internalIncrementTurn();

    void internalCheckForWinConditions();

    void buildPorts();
}
