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

import java.util.List;

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
            setUpNames(setup_names);
            ctx.end();
        });
        ipcRouter.route(HttpMethod.POST, "/set-name").handler((ctx)->{
            final NAME set_name = ctx.getBodyAsJson().mapTo(NAME.class);
            setName(set_name);
        });
        ipcRouter.route(HttpMethod.POST, "/set-password").handler((ctx)->{
            final PASSWORD set_password = ctx.getBodyAsJson().mapTo(PASSWORD.class);
            setPassword(set_password);
        });

        //Get Player Data
        ipcRouter.route(HttpMethod.GET, "/get-player-data/:player_id").handler((ctx)->{
            int player_id = Integer.parseInt(ctx.pathParams().get("player_id"));
            ctx.end(Json.encode(getPlayerData(player_id)));
        });
        ipcRouter.route(HttpMethod.GET, "/get-all-users").handler((ctx)->{
            ctx.end(Json.encode(getAllPlayersData()));
        });
        ipcRouter.route(HttpMethod.GET, "/get-current-player").handler((ctx)->{
            ctx.end(Json.encode(gameState.getTurn()));
        });

        //TODO GET BOARD DATA

        //TODO TRADES

        //TODO CARDS

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


}
