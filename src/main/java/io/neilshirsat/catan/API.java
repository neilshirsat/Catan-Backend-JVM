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

    public static class CURRENT_PLAYER  {

    }


}
