package io.neilshirsat.catan;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
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

        ipcRouter.route().handler(CorsHandler.create("*")
                .addOrigin("http://localhost:3000")
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.OPTIONS)
                .allowCredentials(true)
                .allowedHeader("Access-Control-Allow-Headers")
                .allowedHeader("Authorization")
                .allowedHeader("Access-Control-Allow-Method")
                .allowedHeader("Access-Control-Allow-Origin")
                .allowedHeader("Access-Control-Allow-Credentials")
                .allowedHeader("Content-Type"));

        ipcRouter.route().handler(BodyHandler.create());

        //Debug
        ipcRouter.route(HttpMethod.GET, "/").handler((ctx) -> {
            ctx.end(Json.encode(gameState));
        });

        //SETUP
        ipcRouter.route(HttpMethod.POST, "/setup-names").handler((ctx) -> {
            logger.info(ctx.getBodyAsJson().encode());
            final SETUP_NAMES setup_names = ctx.getBodyAsJson().mapTo(SETUP_NAMES.class);
            setUpNames(setup_names);
            ctx.end(Json.encode(Player.getPlayer(gameState.getTurn())));
        });
        ipcRouter.route(HttpMethod.POST, "/set-name").handler((ctx) -> {
            final NAME set_name = ctx.getBodyAsJson().mapTo(NAME.class);
            setName(set_name);
            ctx.end(Json.encode(Player.getPlayer(gameState.getTurn())));
        });
        ipcRouter.route(HttpMethod.POST, "/set-password").handler((ctx) -> {
            final PASSWORD set_password = ctx.getBodyAsJson().mapTo(PASSWORD.class);
            setPassword(set_password);
            ctx.end(Json.encode(Player.getPlayer(gameState.getTurn())));
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
            ctx.end(Json.encode(Player.getPlayer(gameState.getTurn())));
        });
        ipcRouter.route(HttpMethod.GET, "/next-turn").handler((ctx) -> {
            endTurn();
            ctx.end(Json.encode(Player.getPlayer(gameState.getTurn())));
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
        ipcRouter.route(HttpMethod.GET, "/can-build-edges").handler((ctx) -> {
            List<EdgeImpl> allEdges = EdgeImpl.allEdges();
            for (EdgeImpl e : allEdges) {
                if (e.getControlledPlayer().canBuyFromShop(Player.Shop.CITY)) {

                }
            }
            ctx.end(Json.encode(allEdges));
        });

        ipcRouter.route(HttpMethod.GET, "/get-vertices").handler((ctx) -> {
            List<VertexImpl> allVerticies = VertexImpl.allVerticies();
            ctx.end(Json.encode(allVerticies));
        });
        ipcRouter.route(HttpMethod.GET, "/can-build-settlement-first-turn").handler((ctx) -> {
            List<VertexImpl> allVerticies = VertexImpl.allVerticies();
            List<VertexImpl> vertices = new ArrayList<>();
            for (VertexImpl vertex : allVerticies) {
                if (vertex.canBuildSettlementFirstTurn(Player.getPlayer(gameState.getTurn()))) {
                    vertices.add(vertex);
                }
            }
            ctx.end(Json.encode(vertices));
        });
        ipcRouter.route(HttpMethod.GET, "/can-build-settlement").handler((ctx) -> {
            List<VertexImpl> allVerticies = VertexImpl.allVerticies();
            List<VertexImpl> vertices = new ArrayList<>();
            for (VertexImpl vertex : allVerticies) {
                if (vertex.canBuildSettlementFirstTurn(Player.getPlayer(gameState.getTurn()))) {
                    vertices.add(vertex);
                }
            }
            ctx.end(Json.encode(vertices));
        });
        ipcRouter.route(HttpMethod.GET, "/can-build-city").handler((ctx) -> {
            List<VertexImpl> allVerticies = VertexImpl.allVerticies();
            List<VertexImpl> vertices = new ArrayList<>();
            for (VertexImpl vertex : allVerticies) {
                if (vertex.canBuildCity(Player.getPlayer(gameState.getTurn()))) {
                    vertices.add(vertex);
                }
            }
            ctx.end(Json.encode(vertices));
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


    public static ArrayList<String> gameLog = new ArrayList<>();

    public static class SETUP_NAMES {

        private int amountPlayers;

        /**
         * List with the Player Name and their Passcode
         */
        private String[] playerNames;

        /**
         * List with the Player Passcodes
         */
        private String[] playerPasscodes;

        public int getAmountPlayers() {
            return amountPlayers;
        }

        public void setAmountPlayers(int amountPlayers) {
            this.amountPlayers = amountPlayers;
        }

        public String[] getPlayerNames() {
            return playerNames;
        }

        public void setPlayerNames(String[] playerNames) {
            this.playerNames = playerNames;
        }

        public String[] getPlayerPasscodes() {
            return playerPasscodes;
        }

        public void setPlayerPasscodes(String[] playerPasscodes) {
            this.playerPasscodes = playerPasscodes;
        }
    }

    public void setUpNames(SETUP_NAMES input) {
        NodeImpl.prepareNodeSetup();
        Player.amountPlayers = input.amountPlayers;
        Player.initializeAllPlayers();
        for (int i = 0; i < input.amountPlayers; i++) {
            Player.getPlayer(i + 1).setPlayerName(input.playerNames[i]);
            Player.getPlayer(i + 1).setPasscode(input.playerPasscodes[i]);
        }
    }

    public static class NAME {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;

    }

    public void setName(NAME input) {
        gameState.changePlayerName(input.name, gameState.getTurn());
    }

    public static class PASSWORD {
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        private String password;
    }

    public void setPassword(PASSWORD input) {
        gameState.changePlayerPasscode(input.password, gameState.getTurn());
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

    /*public static class ROLL_DICE {

        List<NodeImpl> deck;

    }*/

    public void rollDice() {
        int diceRoll = gameState.rollDice();
        gameState.handleDiceRoll(diceRoll);

        //CHECK FOR DISCARD CARDS



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
            gameLog.add(p.getPlayerName()+ " discarded "  + k.getValue() + " " + k.getKey().toString());
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

    public List<EdgeImpl> validRoads(Player p) {
        List<EdgeImpl> validRoads = new ArrayList<>();
        for (EdgeImpl edge : EdgeImpl.allEdges()) {
            edge.setControlledPlayer(p);
            if (edge.isValidPlaceRoad()) {
                validRoads.add(edge);
            }
        }
        return validRoads;
    }

    public void purchaseRoad(PURCHASE_ROAD input) {
        if (Player.getPlayer(input.playerId).canBuyFromShop(input.road)) {
            Player.getPlayer(input.playerId).purchase(input.road);
            EdgeImpl edge = (EdgeImpl) EdgeImpl.getEdge(input.edgeId);
            edge.placeRoad(Player.getPlayer(input.playerId));
            gameLog.add(Player.getPlayer(input.playerId).getPlayerName()+" placed Road");
        }
    }

    public List<Vertex> validSettlements(Player p) {
        List<Vertex> validSettlements = new ArrayList<>();
        for (VertexImpl v : VertexImpl.allVerticies()) {
            if (v.canBuildSettlement(p)) {
                validSettlements.add(v);
            }
        }
        return validSettlements;
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
            gameLog.add(Player.getPlayer(input.playerId).getPlayerName()+" placed Settlement");
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
            gameLog.add(Player.getPlayer(input.playerId).getPlayerName()+" placed City");
        }
    }




    public static class CHANGE_ROBBER {
        int nodeID;
        int playerRobbedId;
        int playerRobbingId;
    }

    public void changeRobber(CHANGE_ROBBER input) {
        NodeImpl.changeRobber(input.nodeID, Player.getPlayer(input.playerRobbedId), Player.getPlayer(input.playerRobbingId));
        gameLog.add(Player.getPlayer(input.playerRobbingId).getPlayerName() +" stole from " + Player.getPlayer(input.playerRobbedId).getPlayerName());

    }


    public static class PROPOSE_TRADE {

        Map<ResourceType, Integer> player1Outgoing;

        Map<ResourceType, Integer> player2Outgoing;

        int player1Id;

        Player[] targetPlayers;
    }


    public void proposeTrade(PROPOSE_TRADE input) {
        GameStateImpl.currentTrade currentTrade = new GameStateImpl.currentTrade(input.player1Outgoing, input.player2Outgoing, GameStateImpl.currentTrade.getAmountTrades(), input.targetPlayers);
        gameLog.add(currentTrade.getTradeId()+ ": " +Player.getPlayer(input.player1Id)+ " wants to trade " + input.player1Outgoing.toString() + " for " + input.player2Outgoing);
    }

    public static class VERIFY_TRADE {

        GameStateImpl.currentTrade currentTrade;

        int player1Id;

        String passcode;

    }

    public static void verifyTrade(VERIFY_TRADE input) {

        if (Player.verifyTrade(input.passcode,input.currentTrade.getTradeOutgoing())) {
            for (Player p : Player.getAllPlayers()) {
                if (p.getPasscode().equals(input.passcode))
                    Player.tradeCards(input.currentTrade.getTradeOutgoing(), Player.getPlayer(input.player1Id), input.currentTrade.getTradeIngoing(), p);
                    gameLog.add(Player.getPlayer(input.player1Id).getPlayerName() + " completed " + input.currentTrade.getTradeId()+ " with " + p.getPlayerName());

            }
        }
        input.currentTrade = null;

    }

    public void endTurn() {
        gameState.passDice();

    }


    public static class PURCHASE_DEV_CARD {
        Player p;
        Player.Shop shop;
    }

    public void purchaseDevCard(PURCHASE_DEV_CARD input) {
        if (input.p.canBuyFromShop(input.shop)) {
            input.p.purchase(input.shop);
            gameLog.add(input.p.getPlayerName() + " purchased a Development Card");
        }

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
        gameLog.add(Player.getPlayer(input.playerRobbingId).getPlayerName() + " used Knight Card");
        gameLog.add(Player.getPlayer(input.playerRobbingId).getPlayerName() +" stole from " + Player.getPlayer(input.playerRobbedId).getPlayerName());
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
        gameLog.add(Player.getPlayer(input.playerId).getPlayerName() + " used Knight Card");
        gameLog.add(Player.getPlayer(input.playerId).getPlayerName() + " received " + count + " " +input.resourceType.toString());
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