package io.neilshirsat.catan;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class API extends AbstractVerticle {

    private final Logger logger = LoggerFactory.getLogger(API.class);

    private final HttpServer ipc;

    private final Router ipcRouter;

    private final Handler<Boolean> ipcFinished;

    private final GameStateImpl gameState;

    public API(Handler<Boolean> ipcFinished) {
        super();
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
        ipcRouter.route(HttpMethod.POST, "setup-names").handler((ctx)->{
            //ctx.getBodyAsJson().mapTo()
            //gameState.changePlayerName();
        });
        ipcRouter.route(HttpMethod.POST, "change-name").handler((ctx)->{

        });
        ipcRouter.route(HttpMethod.POST, "change-name").handler((ctx)->{

        });
        ipcRouter.route(HttpMethod.POST, "change-name").handler((ctx)->{

        });


        ipcRouter.route(HttpMethod.GET, "get-current-user").handler((ctx)->{

        });
        ipcRouter.route(HttpMethod.GET, "get-all-users").handler((ctx)->{

        });
        ipcRouter.route(HttpMethod.POST, "change-name").handler((ctx)->{

        });
        ipcRouter.route(HttpMethod.GET, "change-name").handler((ctx)->{

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

    public static class SETUP_NAMES {

    }

}
