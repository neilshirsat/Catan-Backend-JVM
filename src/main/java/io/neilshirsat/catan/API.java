package io.neilshirsat.catan;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        //SETUP
        ipcRouter.route(HttpMethod.POST, "/setup-names").handler((ctx)->{
            final SETUP_NAMES setup_names = ctx.getBodyAsJson().mapTo(SETUP_NAMES.class);
            for (int i = 0; i < setup_names.amountPlayers; i++) {
                gameState.changePlayerName(setup_names.playerNames[i], i);
                gameState.changePlayerPasscode(setup_names.playerPasscodes[i], i);
            }
            logger.info(Json.encode(setup_names));
        });
        ipcRouter.route(HttpMethod.POST, "/change-name").handler((ctx)->{

        });
        ipcRouter.route(HttpMethod.POST, "/change-name").handler((ctx)->{

        });
        ipcRouter.route(HttpMethod.POST, "/change-name").handler((ctx)->{

        });


        ipcRouter.route(HttpMethod.GET, "/get-current-user").handler((ctx)->{
            //CURRENT_PLAYER currentPlayer =
        });
        ipcRouter.route(HttpMethod.GET, "/get-all-users").handler((ctx)->{

        });
        ipcRouter.route(HttpMethod.POST, "/change-name").handler((ctx)->{

        });
        ipcRouter.route(HttpMethod.GET, "/change-name").handler((ctx)->{

        });
    }

    /**
     * Start the Server
     */
    public void startServer() {
        final Future<HttpServer> future = ipc.requestHandler(ipcRouter).listen(6584);
        future.onSuccess((ctx)->{
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
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */



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
        for (int i = 1; i < input.amountPlayers+1; i++) {
            Player.getPlayer(i).setPlayerName(input.playerNames[i]);
            Player.getPlayer(i).setPasscode(input.playerPasscodes[i]);
        }
    }

    public static class NAME   {

        String name;

        int playerId;
    }

    public void setName(NAME input) {
        gameState.changePlayerName(input.name,input.playerId);
    }

    public static class PASSWORD {

        String password;

        int playerId;
    }

    public void setPassword(PASSWORD input) {
        gameState.changePlayerPasscode(input.password, input.playerId);
    }

    public static class PLAYER_DATA {

        int playerId;

        int victoryPoints;

        Map<ResourceType, Integer> deck;

        Map<DevelopmentCards, Integer> developmentCards;

        Map<SpecialCards, Integer> specialCards;
    }

    public Player getPlayerData(PLAYER_DATA input) {
        Player player = Player.getPlayer(input.playerId);
        player.setDeck(input.deck);
        player.setDevelopmentCards(input.developmentCards);
        player.setSpecialCards(input.specialCards);
        return player;
    }

    public List<Player> getAllPlayersData() {
        return (List.of(Player.getPlayer(1),Player.getPlayer(2),Player.getPlayer(3),Player.getPlayer(4)));
    }


}
