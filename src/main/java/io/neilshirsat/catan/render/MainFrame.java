/**
 * Uses JCEF Starter Code- https://bitbucket.org/chromiumembedded/java-cef/src/master/, https://github.com/jcefbuild/jcefbuild
 */

package io.neilshirsat.catan.render;

import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import org.cef.CefApp;
import org.cef.CefApp.CefVersion;
import org.cef.CefClient;
import org.cef.CefSettings;
import org.cef.OS;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.browser.CefMessageRouter;
import org.cef.handler.CefFocusHandlerAdapter;
import org.cef.handler.CefLoadHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class MainFrame extends BrowserFrame {

    private static final Logger Log = LoggerFactory.getLogger(MainFrame.class);
    @Serial
    private static final long serialVersionUID = -2295538706810864538L;
    private static JFrame startupFrame;

    public static void main(String[] args) {

        FlatArcIJTheme.setup();

        if (!CefApp.startup(args)) {
            Log.info("Startup initialization failed!");
            return;
        }

        boolean osrEnabledArg = OS.isLinux();
        boolean transparentPaintingEnabledArg = true;
        boolean createImmediately = true;

        final MainFrame frame = new MainFrame(
                osrEnabledArg, transparentPaintingEnabledArg, createImmediately, args);
        frame.setExtendedState(MAXIMIZED_BOTH);
        frame.setSize(800,400);
        frame.setTitle("Catan Portable Game");

        startupFrame = new JFrame();
        startupFrame.getContentPane().add(new JButton("Loading"));
        startupFrame.setSize(800,400);
        startupFrame.setVisible(true);

    }

    private boolean browserFocus = true;

    public MainFrame(boolean osrEnabled, boolean transparentPaintingEnabled,
            boolean createImmediately, String[] args) {

        CefApp application;
        if (CefApp.getState() != CefApp.CefAppState.INITIALIZED) {
            CefSettings settings = new CefSettings();
            settings.windowless_rendering_enabled = osrEnabled;
            settings.background_color = settings.new ColorType(100, 255, 242, 211);
            application = CefApp.getInstance(args, settings);

            CefVersion version = application.getVersion();
            Log.info("JCEF Version: " + version);

            CefApp.addAppHandler(new AppHandler(args));
        } else {
            application = CefApp.getInstance();
        }

        CefClient cefClient = application.createClient();

        CefMessageRouter msgRouter = CefMessageRouter.create();
        cefClient.addMessageRouter(msgRouter);

        cefClient.addLoadHandler(new CefLoadHandlerAdapter() {

            @Override
            public void onLoadError(CefBrowser browser, CefFrame frame, ErrorCode errorCode,
                    String errorText, String failedUrl) {
                if (errorCode != ErrorCode.ERR_NONE && errorCode != ErrorCode.ERR_ABORTED) {
                    browser.stopLoad();
                }
            }

            @Override
            public void onLoadEnd(CefBrowser cefBrowser, CefFrame cefFrame, int i) {
                setVisible(true);
                startupFrame.setVisible(false);
                startupFrame = null;
            }
        });

        CefBrowser browser = cefClient.createBrowser(
                "http://localhost:3000", osrEnabled, transparentPaintingEnabled, null);
        setBrowser(browser);

        JPanel contentPanel = new JPanel(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        cefClient.addFocusHandler(new CefFocusHandlerAdapter() {
            @Override
            public void onGotFocus(CefBrowser browser) {
                if (browserFocus) return;
                browserFocus = true;
                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
                browser.setFocus(true);
            }

            @Override
            public void onTakeFocus(CefBrowser browser, boolean next) {
                browserFocus = false;
            }
        });

        if (createImmediately) browser.createImmediately();

        contentPanel.add(getBrowser().getUIComponent(), BorderLayout.CENTER);
    }
}
