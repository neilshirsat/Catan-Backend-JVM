/**
 * Uses JCEF Starter Code- https://bitbucket.org/chromiumembedded/java-cef/src/master/, https://github.com/jcefbuild/jcefbuild
 */

package io.neilshirsat.catan.render;

import org.cef.CefApp;
import org.cef.browser.CefBrowser;
import org.cef.handler.CefLifeSpanHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BrowserFrame extends JFrame {

    private static final Logger Log = LoggerFactory.getLogger(BrowserFrame.class);

    private volatile boolean isClosed = false;
    private CefBrowser cefBrowser = null;

    public BrowserFrame() {
        this(null);
    }

    public BrowserFrame(String title) {
        super(title);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (cefBrowser == null) {
                    isClosed = true;
                    Log.info("BrowserFrame.windowClosing Frame.dispose");
                    dispose();
                    return;
                }
                boolean isClosed = BrowserFrame.this.isClosed;
                if (isClosed) {
                    cefBrowser.setCloseAllowed();
                }
                Log.info("BrowserFrame.windowClosing CefBrowser.close(" + isClosed + ")");
                cefBrowser.close(isClosed);
                if (!BrowserFrame.this.isClosed) {
                    BrowserFrame.this.isClosed = true;
                }
                if (isClosed) {
                    Log.info("BrowserFrame.windowClosing Frame.dispose");
                    dispose();
                }
            }
        });
    }

    public void setBrowser(CefBrowser browser) {
        if (cefBrowser == null) cefBrowser = browser;

        cefBrowser.getClient().removeLifeSpanHandler();
        cefBrowser.getClient().addLifeSpanHandler(new CefLifeSpanHandlerAdapter() {
            @Override
            public void onAfterCreated(CefBrowser browser) {
                Log.info("BrowserFrame.onAfterCreated id=" + browser.getIdentifier());
            }

            @Override
            public void onAfterParentChanged(CefBrowser browser) {
                Log.info("BrowserFrame.onAfterParentChanged id=" + browser.getIdentifier());
            }

            @Override
            public boolean doClose(CefBrowser browser) {
                boolean result = browser.doClose();
                Log.info("BrowserFrame.doClose id=" + browser.getIdentifier() + " CefBrowser.doClose=" + result);
                return result;
            }

            @Override
            public void onBeforeClose(CefBrowser browser) {
                Log.info("BrowserFrame.onBeforeClose id=" + browser.getIdentifier());
                CefApp.getInstance().dispose();
            }
        });
    }

    public CefBrowser getBrowser() {
        return cefBrowser;
    }
}
