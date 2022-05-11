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
import io.vertx.ext.web.handler.StaticHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
                .addOrigin("http://localhost:6584")
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
        ipcRouter.route(HttpMethod.GET, "/debug").handler((ctx) -> {
            ctx.end(Json.encode(gameState));
        });

        /**
         *
         *
         *
         *
         * SETUP
         *
         *
         *
         *
         */
        ipcRouter.route(HttpMethod.POST, "/setup-names").blockingHandler((ctx) -> {
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

        /**
         *
         *
         *
         *
         * Player Data
         *
         *
         *
         *
         */
        ipcRouter.route(HttpMethod.GET, "/get-player-data/:player_id").handler((ctx) -> {
            int player_id = Integer.parseInt(ctx.pathParams().get("player_id"));
            ctx.end(Json.encode(getPlayerData(player_id)));
        });
        ipcRouter.route(HttpMethod.GET, "/get-all-users").handler((ctx) -> {
            ctx.end(Json.encode(getAllPlayersData()));
        });
        ipcRouter.route(HttpMethod.GET, "/other-players").handler((ctx) -> {
            List<Player> allPlayers = Player.getAllPlayers();
            List<Player> players = new ArrayList<>();
            for (Player e: allPlayers) {
                if (e.getId() != gameState.getTurn()) {
                    players.add(e);
                }
            }
            logger.info("Other Players: " + Json.encode(players));
            ctx.end(Json.encode(players));
        });
        ipcRouter.route(HttpMethod.GET, "/other-players-trade").handler((ctx) -> {
            ctx.end(Json.encode(canRobPlayerList()));
        });
        ipcRouter.route(HttpMethod.GET, "/get-current-player").handler((ctx) -> {
            ctx.end(Json.encode(Player.getPlayer(gameState.getTurn())));
        });
        ipcRouter.route(HttpMethod.GET, "/next-turn").handler((ctx) -> {
            endTurn();
            ctx.end(Json.encode(Player.getPlayer(gameState.getTurn())));
        });
        ipcRouter.route(HttpMethod.GET, "/current-stage").handler((ctx) -> {
            ctx.end(Json.encode(gameState.getStage()));
        });


        /**
         *
         *
         *
         *
         * Board Data
         *
         *
         *
         *
         */
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

        /**
         *
         *
         *
         *
         * Roll the Dice
         *
         *
         *
         *
         */
        ipcRouter.route(HttpMethod.GET, "/roll-dice").handler((ctx) -> {
            int dice = gameState.rollDice();
            gameState.handleDiceRoll(dice);
            ctx.end(Json.encode(dice));
        });

        /**
         *
         *
         *
         *
         * Special Case 7
         *
         *
         *
         *
         */
        ipcRouter.route(HttpMethod.GET, "/overflow-deck-players").handler((ctx) -> {
            List<Player> allPlayers = Player.getAllPlayers();
            List<Player> players = new ArrayList<>();
            for (Player e: allPlayers) {
                if (e.getId() != gameState.getTurn()) {
                    if (e.getAmountResourceCards() >= 8) {
                        players.add(e);
                    }
                }
            }
            logger.info("Other Players: " + Json.encode(players));
            ctx.end(Json.encode(players));
        });
        ipcRouter.route(HttpMethod.GET, "/can-place-robber-nodes").handler((ctx) -> {
            List<NodeImpl> allNodes = NodeImpl.getAllNodes();
            List<Integer> nodes = new ArrayList<>(18);
            for (NodeImpl node : allNodes) {
                if (!node.isHasRobber()) {
                    nodes.add(node.getNodeId());
                }
            }
            ctx.end(Json.encode(nodes));
        });
        ipcRouter.route(HttpMethod.POST, "/discard-cards").handler((ctx) -> {
            DISCARD_CARD_WHEN_ROLL_7 rob_player = ctx.getBodyAsJson().mapTo(DISCARD_CARD_WHEN_ROLL_7.class);
            discardCards(rob_player);
            ctx.end();
        });
        ipcRouter.route(HttpMethod.POST, "/move-robber").handler((ctx) -> {
            CHANGE_ROBBER change_robber = ctx.getBodyAsJson().mapTo(CHANGE_ROBBER.class);
            changeRobber(change_robber);
            ctx.end();
        });
        ipcRouter.route(HttpMethod.GET, "/get-players-to-rob").handler((ctx) -> {
            ctx.end(Json.encode(canRobPlayerList()));
        });
        ipcRouter.route(HttpMethod.POST, "/rob-cards").handler((ctx) -> {
            ROB_PLAYER rob_player = ctx.getBodyAsJson().mapTo(ROB_PLAYER.class);
            ctx.end(Json.encode(robPlayer(rob_player)));
        });

        /**
         *
         *
         *
         *
         * Build Settlements, Cities, and Roads
         *
         *
         *
         *
         */
        ipcRouter.route(HttpMethod.POST, "/build-settlement").handler((ctx) -> {
            final PURCHASE_SETTLEMENT purchase_settlement = ctx.getBodyAsJson().mapTo(PURCHASE_SETTLEMENT.class);
            if (gameState.getStage() != GameStateImpl.Stage.STAGE_3) {
                purchaseSettlementStage1and2(purchase_settlement);
                if (gameState.getStage() == GameStateImpl.Stage.STAGE_2) {
                    List<NodeImpl> nodes = new ArrayList<>();
                    for (int e: VertexImpl.getVertex(purchase_settlement.vertexId).getConnectedNodes()) {
                        nodes.add((NodeImpl) NodeImpl.getNode(e));
                    }
                    NodeImpl.receiveCardsStage2(nodes,Player.getPlayer(gameState.getTurn()));
                }
            }
            else {
                purchaseSettlement(purchase_settlement);
            }
            ctx.end(Json.encode(VertexImpl.allVerticies()));
        });

        ipcRouter.route(HttpMethod.POST, "/build-city").handler((ctx) -> {
            final PURCHASE_CITY purchase_city = ctx.getBodyAsJson().mapTo(PURCHASE_CITY.class);
            purchaseSettlement(purchase_city);
            ctx.end(Json.encode(VertexImpl.allVerticies()));
        });

        ipcRouter.route(HttpMethod.POST, "/build-road").handler((ctx) -> {
            final PURCHASE_ROAD purchase_road = ctx.getBodyAsJson().mapTo(PURCHASE_ROAD.class);
            if (gameState.getStage() != GameStateImpl.Stage.STAGE_3) {
                purchaseRoadStage1and2(purchase_road);
            }
            else {
                purchaseRoad(purchase_road);
            }
            ctx.end(Json.encode(EdgeImpl.allEdges()));
        });

        /**
         *
         *
         *
         *
         * Can Build Settlements, Cities, and Roads
         *
         *
         *
         *
         */
        ipcRouter.route(HttpMethod.GET, "/can-build-roads").handler((ctx) -> {
            List<Integer> edges = new ArrayList<>();
            if (gameState.getStage() != GameStateImpl.Stage.STAGE_3) {
                for (int e : Player.getPlayer(gameState.getTurn()).getPreviousBuiltNode().getConnectedEdges()) {
                    if (!(EdgeImpl.getEdge(e)).isRoad()) {
                        edges.add(e);
                    }
                }
            }
            else {
                for (VertexImpl e: VertexImpl.allVerticies()) {
                    for (int a: e.getConnectedEdges()) {
                        if (!((EdgeImpl) EdgeImpl.getEdge(a)).isRoad()) {
                            edges.add(a);
                        }
                    }
                }
            }
            ctx.end(Json.encode(edges));
            //ctx.end(Json.encode(validRoads(Player.getPlayer(gameState.getTurn()))));
        });
        ipcRouter.route(HttpMethod.GET, "/can-build-settlement-first-turn").handler((ctx) -> {
            List<VertexImpl> allVerticies = VertexImpl.allVerticies();
            List<Integer> vertices = new ArrayList<>();
            for (VertexImpl vertex : allVerticies) {
                if (vertex.canBuildSettlementFirstTurn(Player.getPlayer(gameState.getTurn()))) {
                    vertices.add(vertex.vertxId);
                }
            }
            ctx.end(Json.encode(vertices));
        });
        ipcRouter.route(HttpMethod.GET, "/can-build-settlement").handler((ctx) -> {
            List<VertexImpl> allVerticies = VertexImpl.allVerticies();
            List<Integer> vertices = new ArrayList<>();
            for (VertexImpl vertex : allVerticies) {
                if (vertex.canBuildSettlement(Player.getPlayer(gameState.getTurn()))) {
                    vertices.add(vertex.vertxId);
                }
            }
            ctx.end(Json.encode(vertices));
        });
        ipcRouter.route(HttpMethod.GET, "/can-build-city").handler((ctx) -> {
            List<VertexImpl> allVerticies = VertexImpl.allVerticies();
            List<Integer> vertices = new ArrayList<>();
            for (VertexImpl vertex : allVerticies) {
                if (vertex.canBuildCity(Player.getPlayer(gameState.getTurn()))) {
                    vertices.add(vertex.vertxId);
                }
            }
            ctx.end(Json.encode(vertices));
        });

        /**
         *
         *
         *
         *
         * Trade with Other Players
         *
         *
         *
         *
         */
        ipcRouter.route(HttpMethod.POST, "/initiate-trade").handler((ctx) -> {
            PROPOSE_TRADE propose_trade = ctx.getBodyAsJson().mapTo(PROPOSE_TRADE.class);
            proposeTrade(propose_trade);
            ctx.end(Json.encode(gameState.getCurrentTrades()));
        });
        ipcRouter.route(HttpMethod.POST, "/complete-trade").handler((ctx) -> {
            VERIFY_TRADE verify_trade = ctx.getBodyAsJson().mapTo(VERIFY_TRADE.class);
            verifyTrade(verify_trade);
            ctx.end(Json.encode(gameState.getCurrentTrades()));
        });

        /**
         *
         *
         *
         *
         * Trade with Bank/Port
         *
         *
         *
         *
         */
        ipcRouter.route(HttpMethod.POST, "/trade-with-bank").handler((ctx) -> {
            TRADE_WITH_BANK TRADE_WITH_BANK = ctx.getBodyAsJson().mapTo(TRADE_WITH_BANK.class);
            tradeWithBank(TRADE_WITH_BANK);
        });

        /**
         *
         *
         *
         *
         * Development Cards
         *
         *
         *
         *
         */
        ipcRouter.route(HttpMethod.GET, "/buy-development-card").handler((ctx) -> {
            PURCHASE_DEV_CARD purchase_dev_card = ctx.getBodyAsJson().mapTo(PURCHASE_DEV_CARD.class);
            purchaseDevCard(purchase_dev_card);
            ctx.end(Json.encode(Player.getPlayer(gameState.getTurn())));
        });
        ipcRouter.route(HttpMethod.GET, "/use-year-of-plenty").handler((ctx) -> {
            USE_YEAR_OF_PLENTY use_year_of_plenty = ctx.getBodyAsJson().mapTo(USE_YEAR_OF_PLENTY.class);
            useYearOfPlenty(use_year_of_plenty);
            ctx.end(Json.encode(Player.getPlayer(gameState.getTurn())));
        });
        ipcRouter.route(HttpMethod.GET, "/use-dev-card").handler((ctx) -> {
            USE_DEV_CARD use_dev_card = ctx.getBodyAsJson().mapTo(USE_DEV_CARD.class);
            useDevCard(use_dev_card);
            ctx.end(Json.encode(Player.getPlayer(gameState.getTurn())));
        });
        ipcRouter.route(HttpMethod.GET, "/use-knight").handler((ctx) -> {
            USE_KNIGHT use_knight = ctx.getBodyAsJson().mapTo(USE_KNIGHT.class);
            useKnight(use_knight);
            ctx.end(Json.encode(Player.getPlayer(gameState.getTurn())));
        });
        ipcRouter.route(HttpMethod.GET, "/use-road-building").handler((ctx) -> {
            USE_ROAD_BUILDING use_road_building = ctx.getBodyAsJson().mapTo(USE_ROAD_BUILDING.class);
            useRoadBuilding(use_road_building);
            ctx.end(Json.encode(Player.getPlayer(gameState.getTurn())));
        });
        ipcRouter.route(HttpMethod.GET, "/use-monopoly").handler((ctx) -> {
            USE_MONOPOLY use_monopoly = ctx.getBodyAsJson().mapTo(USE_MONOPOLY.class);
            useMonopoly(use_monopoly);
            ctx.end(Json.encode(Player.getPlayer(gameState.getTurn())));
        });

        /**
         *
         *
         *
         *
         * Win Conditions
         *
         *
         *
         *
         */
        ipcRouter.route(HttpMethod.GET, "/check-win-conditions").handler((ctx) -> {
            ctx.end(Json.encode(this.gameState.checkPlayerWin()));
        });
        ipcRouter.route(HttpMethod.GET, "/port-list").handler((ctx) -> {
            final List<VertexImpl.Port> portList = VertexImpl.getPortList();
            ctx.end(Json.encode(portList));
        });
        ipcRouter.route(HttpMethod.GET, "/port-vertices").handler((ctx) -> {
            List<Integer> portVertices = VertexImpl.PortVertices();
            ctx.end(Json.encode(portVertices));
        });


        ipcRouter.route().handler(StaticHandler.create());

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

    public static class DISTRIBUTE_CARDS {

        private int dice;

    }

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

        public Player.COLOR[] getPlayerColors() {
            return playerColors;
        }

        public void setPlayerColors(Player.COLOR[] playerColors) {
            this.playerColors = playerColors;
        }

        private Player.COLOR[] playerColors;

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
            Player.getPlayer(i + 1).setColor(input.playerColors[i]);
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

    public List<Integer> getPorts() {
        return VertexImpl.PortVertices();
    }

    public Map<ResourceType, Integer> amountResourceCardsLeft() {
        Map<ResourceType, Integer> map = new TreeMap<>();
        map.put(ResourceType.ORE, ResourceType.ORE.getAmountLeft());
        map.put(ResourceType.WOOL, ResourceType.WOOL.getAmountLeft());
        map.put(ResourceType.WHEAT, ResourceType.WHEAT.getAmountLeft());
        map.put(ResourceType.LUMBER, ResourceType.LUMBER.getAmountLeft());
        map.put(ResourceType.BRICK, ResourceType.BRICK.getAmountLeft());
       return map;
    }

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
        static Player.Shop road = Player.Shop.ROAD;

        public int getEdgeId() {
            return edgeId;
        }

        public void setEdgeId(int edgeId) {
            this.edgeId = edgeId;
        }

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

    public void purchaseRoadStage1and2(PURCHASE_ROAD input) {
        EdgeImpl edge = (EdgeImpl) EdgeImpl.getEdge(input.edgeId);
        edge.placeRoadStage1and2(Player.getPlayer(gameState.getTurn()));
        gameLog.add(Player.getPlayer(gameState.getTurn()).getPlayerName()+" placed Road");
    }

    public void purchaseRoad(PURCHASE_ROAD input) {
        if (Player.getPlayer(gameState.getTurn()).canBuyFromShop(input.road)) {
            Player.getPlayer(gameState.getTurn()).purchase(input.road);
            EdgeImpl edge = (EdgeImpl) EdgeImpl.getEdge(input.edgeId);
            edge.placeRoad(Player.getPlayer(gameState.getTurn()));
            gameLog.add(Player.getPlayer(gameState.getTurn()).getPlayerName()+" placed Road");
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
        public int getVertexId() {
            return vertexId;
        }

        public void setVertexId(int vertexId) {
            this.vertexId = vertexId;
        }

        static Player.Shop settlement = Player.Shop.SETTLEMENT;
        int vertexId;
    }

    public void purchaseSettlement(PURCHASE_SETTLEMENT input) {
        if (Player.getPlayer(gameState.getTurn()).canBuyFromShop(input.settlement)) {
            Player.getPlayer(gameState.getTurn()).purchase(input.settlement);
        }
        if (VertexImpl.getVertex(input.vertexId).canBuildSettlement(Player.getPlayer(gameState.getTurn()))) {
            VertexImpl.getVertex(input.vertexId).buildSettlement(Player.getPlayer(gameState.getTurn()));
            gameLog.add(Player.getPlayer(gameState.getTurn()).getPlayerName()+" placed Settlement");
        }
    }

    public void purchaseSettlementStage1and2(PURCHASE_SETTLEMENT input) {
        VertexImpl.getVertex(input.vertexId).buildSettlement(Player.getPlayer(gameState.getTurn()));
        Player.getPlayer(gameState.getTurn()).setPreviousBuiltNode(VertexImpl.getVertex(input.vertexId));
        gameLog.add(Player.getPlayer(gameState.getTurn()).getPlayerName()+" placed Settlement");
    }

    public static class PURCHASE_CITY {

        public int getVertexId() {
            return vertexId;
        }

        public void setVertexId(int vertexId) {
            this.vertexId = vertexId;
        }

        static Player.Shop city = Player.Shop.CITY;
        int vertexId;
    }

    public void purchaseSettlement(PURCHASE_CITY input) {
        if (Player.getPlayer(gameState.getTurn()).canBuyFromShop(input.city)) {
            Player.getPlayer(gameState.getTurn()).purchase(input.city);
        }
        if (VertexImpl.getVertex(input.vertexId).canBuildCity(Player.getPlayer(gameState.getTurn()))) {
            VertexImpl.getVertex(input.vertexId).buildCity(Player.getPlayer(gameState.getTurn()));
            gameLog.add(Player.getPlayer(gameState.getTurn()).getPlayerName()+" placed City");
        }
    }

    //For when roll 7/knight card
    //returns list of players who you can rob
    public List<Player> canRobPlayerList() {
        List<Player> playerList = new ArrayList<>();
        for (int vertex : NodeImpl.robber.getVertices()) {
            Player p = VertexImpl.getVertex(vertex).getControlledPlayer();
            if (p == null) {
                continue;
            }
            if (p.getAmountResourceCards()>=1) {
                playerList.add(p);
            }
        }
        return playerList;
    }

    public static class CHANGE_ROBBER {
        public int getNodeId() {
            return nodeId;
        }

        public void setNodeId(int nodeId) {
            this.nodeId = nodeId;
        }

        int nodeId;
    }

    public void changeRobber(CHANGE_ROBBER input) {
        NodeImpl.changeRobber(input.nodeId);
    }

    public static class ROB_PLAYER {
        public int getPlayerRobbingId() {
            return playerRobbingId;
        }

        public void setPlayerRobbingId(int playerRobbingId) {
            this.playerRobbingId = playerRobbingId;
        }

        public int getPlayerRobbedId() {
            return playerRobbedId;
        }

        public void setPlayerRobbedId(int playerRobbedId) {
            this.playerRobbedId = playerRobbedId;
        }

        int playerRobbingId;
        int playerRobbedId;
    }

    public ResourceType robPlayer(ROB_PLAYER input) {
        ResourceType card = Player.robAnotherPlayer(Player.getPlayer(input.playerRobbedId), Player.getPlayer(input.playerRobbingId));
        gameLog.add(Player.getPlayer(input.playerRobbingId).getPlayerName() +" stole from " + Player.getPlayer(input.playerRobbedId).getPlayerName());
        return card;
    }

    public static class PROPOSE_TRADE {

        public Map<ResourceType, Integer> getPlayer1Outgoing() {
            return player1Outgoing;
        }

        public void setPlayer1Outgoing(Map<ResourceType, Integer> player1Outgoing) {
            this.player1Outgoing = player1Outgoing;
        }

        public Map<ResourceType, Integer> getPlayer2Outgoing() {
            return player2Outgoing;
        }

        public void setPlayer2Outgoing(Map<ResourceType, Integer> player2Outgoing) {
            this.player2Outgoing = player2Outgoing;
        }

        public int getPlayer1Id() {
            return player1Id;
        }

        public void setPlayer1Id(int player1Id) {
            this.player1Id = player1Id;
        }

        public int[] getTargetPlayers() {
            return targetPlayers;
        }

        public void setTargetPlayers(int[] targetPlayers) {
            this.targetPlayers = targetPlayers;
        }

        Map<ResourceType, Integer> player1Outgoing;

        Map<ResourceType, Integer> player2Outgoing;

        int player1Id;

        int[] targetPlayers;
    }


    public void proposeTrade(PROPOSE_TRADE input) {
        List<Player> players = new ArrayList<>();
        for (int player: input.targetPlayers) {
            players.add(Player.getPlayer(player));
        }
        GameStateImpl.currentTrade currentTrade = new GameStateImpl.currentTrade(input.player1Outgoing, input.player2Outgoing, GameStateImpl.currentTrade.getAmountTrades(), players);
        gameLog.add(currentTrade.getTradeId()+ ": " +Player.getPlayer(input.player1Id)+ " wants to trade " + input.player1Outgoing.toString() + " for " + input.player2Outgoing);
        gameState.getCurrentTrades().add(currentTrade);
    }

    public static class VERIFY_TRADE {

        public GameStateImpl.currentTrade getCurrentTrade() {
            return currentTrade;
        }

        public void setCurrentTrade(GameStateImpl.currentTrade currentTrade) {
            this.currentTrade = currentTrade;
        }

        public int getPlayer1Id() {
            return player1Id;
        }

        public void setPlayer1Id(int player1Id) {
            this.player1Id = player1Id;
        }

        public String getPasscode() {
            return passcode;
        }

        public void setPasscode(String passcode) {
            this.passcode = passcode;
        }

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

    public static class TRADE_WITH_BANK {

        int playerId;

        Map<ResourceType, Integer> tradeOutgoing;

        ResourceType resourceGiven;

        ResourceType resourceNeeded;
    }

    public void tradeWithBank(TRADE_WITH_BANK input) {
        if (Player.getPlayer(input.playerId).canTradeWithBank(input.tradeOutgoing, input.resourceGiven, input.resourceNeeded)){
            Player.getPlayer(input.playerId).tradeWithBank(input.resourceGiven, input.resourceNeeded);
        }
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


        NodeImpl.changeRobber(input.nodeID);
        Player.robAnotherPlayer(Player.getPlayer(input.playerRobbedId), Player.getPlayer(input.playerRobbingId));
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