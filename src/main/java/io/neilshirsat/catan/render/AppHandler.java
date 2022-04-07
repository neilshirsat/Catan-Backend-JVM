/**
 * Uses JCEF Starter Code- https://bitbucket.org/chromiumembedded/java-cef/src/master/, https://github.com/jcefbuild/jcefbuild
 */

package io.neilshirsat.catan.render;

import org.cef.CefApp.CefAppState;
import org.cef.handler.CefAppHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppHandler extends CefAppHandlerAdapter {

    private static final Logger Log = LoggerFactory.getLogger(AppHandler.class);

    public AppHandler(String[] args) {
        super(args);
    }

    @Override
    public void stateHasChanged(CefAppState state) {
        Log.info("AppHandler.stateHasChanged: " + state);
        if (state == CefAppState.TERMINATED) System.exit(0);
    }
}
