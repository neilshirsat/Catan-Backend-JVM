package io.neilshirsat.catan;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class API extends AbstractVerticle {

    private final Logger logger = LoggerFactory.getLogger(API.class);

    private final HttpServer ipc;

    private final Router ipcRouter;

    private final Handler<Boolean> ipcFinished;

    private final GameStateImpl gameState;

    public API(Handler<Boolean> ipcFinished) {
        super();
        vertx = Vertx.vertx();
        this.ipcFinished = ipcFinished;
        ipc = vertx.createHttpServer();
        ipcRouter = Router.router(vertx);
        gameState = new GameStateImpl();
    }


    /**
     * Initialize
     */
    public void initialize() {

        //Debug
        ipcRouter.route(HttpMethod.GET, "/").handler((ctx) -> {
            ctx.end(Json.encode(gameState));
        });

        //SETUP
        ipcRouter.route(HttpMethod.POST, "/setup-names").handler((ctx) -> {
            final SETUP_NAMES setup_names = ctx.getBodyAsJson().mapTo(SETUP_NAMES.class);
            setUpNames(setup_names);
            ctx.end();
        });
        ipcRouter.route(HttpMethod.POST, "/set-name").handler((ctx) -> {
            final NAME set_name = ctx.getBodyAsJson().mapTo(NAME.class);
            setName(set_name);
        });
        ipcRouter.route(HttpMethod.POST, "/set-password").handler((ctx) -> {
            final PASSWORD set_password = ctx.getBodyAsJson().mapTo(PASSWORD.class);
            setPassword(set_password);
        });

        //Get Player Data
        ipcRouter.route(HttpMethod.GET, "/get-player-data/:player_id").handler((ctx) -> {
            int player_id = Integer.parseInt(ctx.pathParams().get("player_id"));
            ctx.end(Json.encode(getPlayerData(player_id)));
        });
        ipcRouter.route(HttpMethod.GET, "/get-all-users").handler((ctx) -> {
            ctx.end(Json.encode(getAllPlayersData()));
        });
        ipcRouter.route(HttpMethod.GET, "/get-current-player").handler((ctx) -> {
            ctx.end(Json.encode(gameState.getTurn()));
        });

        //TODO GET BOARD DATA
        ipcRouter.route(HttpMethod.GET, "/get-nodes").handler((ctx) -> {
            List<NodeImpl> allNodes = NodeImpl.getAllNodes();
            ctx.end(Json.encode(allNodes));
        });
        ipcRouter.route(HttpMethod.GET, "/get-edges").handler((ctx) -> {
            List<EdgeImpl> allEdges = EdgeImpl.allEdges();
            ctx.end(Json.encode(allEdges));
        });
        ipcRouter.route(HttpMethod.GET, "/get-vertices").handler((ctx) -> {
            List<VertexImpl> allVerticies = VertexImpl.allVerticies();
            ctx.end(Json.encode(allVerticies));
        });

        //TODO TRADES

        //TODO CARDS

    }

    /**
     * Start the Server
     */
    public void startServer() {
        final Future<HttpServer> future = ipc.requestHandler(ipcRouter).listen(6584);
        future.onSuccess((ctx) -> {
            logger.info("IPC STARTED");
            ipcFinished.handle(true);
        });
    }

    /**
     * Dispose of the Server
     */
    public void disposeServer() {
        ipc.close();
    }

    /**
     *
     */


    public ArrayList<String> gameLog = new ArrayList<>();

    public static class SETUP_NAMES {

        int amountPlayers;

        /**
         * List with the Player Name and their Passcode
         */
        String[] playerNames;

        /**
         * List with the Player Passcodes
         */
        String[] playerPasscodes;
    }

    public void setUpNames(SETUP_NAMES input) {
        Player.amountPlayers = input.amountPlayers;
        for (int i = 1; i < input.amountPlayers + 1; i++) {
            Player.getPlayer(i).setPlayerName(input.playerNames[i]);
            Player.getPlayer(i).setPasscode(input.playerPasscodes[i]);
        }
    }

    public static class NAME {

        String name;

        int playerId;
    }

    public void setName(NAME input) {
        gameState.changePlayerName(input.name, input.playerId);
    }

    public static class PASSWORD {

        String password;

        int playerId;
    }

    public void setPassword(PASSWORD input) {
        gameState.changePlayerPasscode(input.password, input.playerId);
    }

    public Player getPlayerData(int input) {
        return Player.getPlayer(input);
    }

    public List<String> getAllPlayersData() {
        return (List.of(
                Json.encode(Player.getPlayer(1)),
                Json.encode(Player.getPlayer(2)),
                Json.encode(Player.getPlayer(3)),
                Json.encode(Player.getPlayer(4)))
        );
    }

    public static class ROLL_DICE {

        List<NodeImpl> deck;

    }

    public void rollDice(ROLL_DICE input) {

    }


    public static class DISCARD_CARD_WHEN_ROLL_7 {

        Map<ResourceType, Integer> discardCard;

        int playerId;

    }

    public void discardCards(DISCARD_CARD_WHEN_ROLL_7 input) {
        Player p = Player.getPlayer(input.playerId);
        for (Map.Entry<ResourceType, Integer> k : input.discardCard.entrySet()) {
            p.getDeck().put(k.getKey(), p.getDeck().get(k.getKey()) - k.getValue());
            k.getKey().setAmountLeft(k.getValue());
            gameLog.add(p.getPlayerName()+ " discarded "  + k.getValue() + k.getKey().toString())
        }


    }

    public static class GET_NODE {
        int getNodeId;
    }

    public Node getNode(GET_NODE input) {
        return NodeImpl.getNode(input.getNodeId);
    }

    public static class GET_EDGE {
        int getEdgeId;
    }

    public Edge getEdge(GET_EDGE input) {
        return EdgeImpl.getEdge(input.getEdgeId);
    }

    public static class GET_VERTEX {
        int getVertexId;
    }

    public Vertex getVertex(GET_VERTEX input) {
        return VertexImpl.getVertex(input.getVertexId);
    }

    public static class PURCHASE_ROAD {
        int playerId;
        Player.Shop road;
        int edgeId;
    }

    public void purchaseRoad(PURCHASE_ROAD input) {
        if (Player.getPlayer(input.playerId).canBuyFromShop(input.road)) {
            Player.getPlayer(input.playerId).purchase(input.road);
            EdgeImpl edge = (EdgeImpl) EdgeImpl.getEdge(input.edgeId);
            edge.placeRoad(Player.getPlayer(input.playerId));
        }
    }

    public static class PURCHASE_SETTLEMENT {
        int playerId;
        Player.Shop settlement;
        int vertexId;
    }

    public void purchaseSettlement(PURCHASE_SETTLEMENT input) {
        if (Player.getPlayer(input.playerId).canBuyFromShop(input.settlement)) {
            Player.getPlayer(input.playerId).purchase(input.settlement);
        }
        if (VertexImpl.getVertex(input.vertexId).canBuildSettlement(Player.getPlayer(input.playerId))) {
            VertexImpl.getVertex(input.vertexId).buildSettlement(Player.getPlayer(input.playerId));
        }
    }

    public static class PURCHASE_CITY {
        int playerId;
        Player.Shop city;
        int vertexId;
    }

    public void purchaseSettlement(PURCHASE_CITY input) {
        if (Player.getPlayer(input.playerId).canBuyFromShop(input.city)) {
            Player.getPlayer(input.playerId).purchase(input.city);
        }
        if (VertexImpl.getVertex(input.vertexId).canBuildCity(Player.getPlayer(input.playerId))) {
            VertexImpl.getVertex(input.vertexId).buildCity(Player.getPlayer(input.playerId));
        }
    }




    public static class CHANGE_ROBBER {
        int nodeID;
        int playerRobbedId;
        int playerRobbingId;
    }

    public void changeRobber(CHANGE_ROBBER input) {
        NodeImpl.changeRobber(input.nodeID, Player.getPlayer(input.playerRobbedId), Player.getPlayer(input.playerRobbingId));
    }


    public static class PROPOSE_TRADE {

        Map<ResourceType, Integer> player1Outgoing;

        Map<ResourceType, Integer> player2Outgoing;

        int player1Id;

        Player[] targetPlayers;
    }


    public static void proposeTrade(PROPOSE_TRADE input) {
        GameStateImpl.currentTrade currentTrade = new GameStateImpl.currentTrade(input.player1Outgoing, input.player2Outgoing, GameStateImpl.currentTrade.getAmountTrades(), input.targetPlayers);
    }

    public static class VERIFY_TRADE {

        GameStateImpl.currentTrade currentTrade;

        int player1Id;

        String passcode;

    }

    public static void verifyTrade(VERIFY_TRADE input) {

        if (Player.verifyTrade(input.passcode, input.currentTrade.getTradeOutgoing())) {
            for (Player p : Player.getAllPlayers()) {
                if (p.getPasscode().equals(input.passcode))
                    Player.tradeCards(input.currentTrade.getTradeOutgoing(), Player.getPlayer(input.player1Id), input.currentTrade.getTradeIngoing(), p);
            }
        }

    }

    public static class END_TURN {

        boolean clicked;
    }

    public void endTurn(END_TURN input) {
        if (input.clicked) {
            GameStateImpl.passDice();
        }
    }


    public static class PURCHASE_DEV_CARD {
        Player p;
        Player.Shop shop;
    }

    public void purchaseDevCard(PURCHASE_DEV_CARD input) {
        if (input.p.canBuyFromShop(input.shop)) {
            input.p.purchase(input.shop);
        }
        ;
    }

    public static class USE_DEV_CARD {

        int playerId;

        DevelopmentCards developmentCards;
    }

    public void useDevCard(USE_DEV_CARD input) {

    }

    public static class USE_KNIGHT {

        int nodeID;

        int playerRobbingId;

        int playerRobbedId;
    }

    public void useKnight(USE_KNIGHT input) {
        NodeImpl.changeRobber(input.nodeID, Player.getPlayer(input.playerRobbedId), Player.getPlayer(input.playerRobbingId));
        Player.getPlayer(input.playerRobbingId).setArmySize(1);
        Player.getPlayer(input.playerRobbingId).getDevelopmentCards().put(DevelopmentCards.KNIGHT, Player.getPlayer(input.playerRobbingId).getDevelopmentCards().get(DevelopmentCards.KNIGHT) - 1);
    }

    public static class USE_MONOPOLY {

        int playerId;

        ResourceType resourceType;

    }

    public void useMonopoly(USE_MONOPOLY input) {

        int count = 0;
        for (Player p : Player.getAllPlayers()) {
            count += p.getDeck().get(input.resourceType);
            p.getDeck().put(input.resourceType, 0);
        }
        Player.getPlayer(input.playerId).getDeck().put(input.resourceType, count);
        Player.getPlayer(input.playerId).getDevelopmentCards().put(DevelopmentCards.MONOPOLY, Player.getPlayer(input.playerId).getDevelopmentCards().get(DevelopmentCards.MONOPOLY) - 1);
    }

    public static class USE_YEAR_OF_PLENTY {

        int playerId;

        ResourceType resourceType1;

        ResourceType resourceType2;
    }

    public void useYearOfPlenty(USE_YEAR_OF_PLENTY input) {
        if (!input.resourceType1.isEmpty()) {
            input.resourceType1.setAmountLeft(-1);
            Player.getPlayer(input.playerId).getDeck().put(input.resourceType1, Player.getPlayer(input.playerId).getDeck().get(input.resourceType1) + 1);
        }
        if (!input.resourceType1.isEmpty()) {
            input.resourceType2.setAmountLeft(-1);
            Player.getPlayer(input.playerId).getDeck().put(input.resourceType2, Player.getPlayer(input.playerId).getDeck().get(input.resourceType2) + 1);
        }
        Player.getPlayer(input.playerId).getDevelopmentCards().put(DevelopmentCards.YEAR_OF_PLENTY, Player.getPlayer(input.playerId).getDevelopmentCards().get(DevelopmentCards.YEAR_OF_PLENTY) - 1);
    }

    public static class USE_ROAD_BUILDING {

        int edgeID1;

        int edgeID2;

        int playerId;

    }

    public void useRoadBuilding(USE_ROAD_BUILDING input) {
        EdgeImpl edge = (EdgeImpl) EdgeImpl.getEdge(input.edgeID1);
        edge.placeRoad(Player.getPlayer(input.playerId));
        edge = (EdgeImpl) EdgeImpl.getEdge(input.edgeID2);
        edge.placeRoad(Player.getPlayer(input.playerId));

    }

    public static class GET_DECK {
        String passcode;
        String inp;
    }


    public Map<ResourceType, Integer> getDeck(GET_DECK input) {
        if (input.passcode.equals(input.inp)) {
            Player p = Player.getPlayer(Integer.parseInt(input.inp));
            return p.getDeck();
        }
        return null;
    }


}