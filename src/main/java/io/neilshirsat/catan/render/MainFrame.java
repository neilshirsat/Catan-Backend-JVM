/**
 * Uses JCEF Starter Code- https://bitbucket.org/chromiumembedded/java-cef/src/master/, https://github.com/jcefbuild/jcefbuild
 */

package io.neilshirsat.catan.render;

import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import io.neilshirsat.catan.API;
import org.cef.*;
import org.cef.CefApp.CefVersion;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.browser.CefMessageRouter;
import org.cef.handler.CefFocusHandlerAdapter;
import org.cef.handler.CefLoadHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class MainFrame extends BrowserFrame {

    private static final Logger Log = LoggerFactory.getLogger(MainFrame.class);
    @Serial
    private static final long serialVersionUID = -2295538706810864538L;
    private static JFrame startupFrame;
    private static API api;

    public static void main(String[] args) throws IOException {

        FlatArcIJTheme.setup();

        startupFrame = new JFrame();
        startupFrame.getContentPane().add(new JButton("Loading"));
        startupFrame.setSize(800,400);
        startupFrame.setVisible(true);

        MainFrame.api = new API((started)->{

        });
        api.initialize();
        api.startServer();

        InputStream binFolder = null;

        if (OS.isWindows()) {
            binFolder = MainFrame.class.getResourceAsStream("win64.zip");
        }
        else if (OS.isLinux()) {
            binFolder = MainFrame.class.getResourceAsStream("linux64.zip");
        }
        else if (OS.isMacintosh()) {
            binFolder = MainFrame.class.getResourceAsStream("mac64.zip");
        }

        assert binFolder != null;

        Path tempBinDirectory = Files.createTempDirectory("bin");
        Log.info(tempBinDirectory.toFile().getAbsolutePath());
        Log.info("Is Windows" + OS.isWindows());
        Log.info("Is Linux" + OS.isLinux());
        Log.info("Is Mac" + OS.isMacintosh());

        try (ZipInputStream zipInputStream = new ZipInputStream(binFolder)) {
            extract(zipInputStream, tempBinDirectory.toFile());
        }

        SystemBootstrap.setLoader(s -> {
            Log.info(s);
            if (OS.isWindows()) {
                try {
                    Log.info("Loading Library" + s);
                    //throw new UnsatisfiedLinkError();
                    System.loadLibrary(s);
                } catch (Throwable e) {
                    Log.info("Not on Temp Path"+s);
                    loadWindows(tempBinDirectory, s);
                }
                finally {
                    Log.info("Loaded" + s);
                }
            }
        });

        if (!CefApp.startup(args)) {
            Log.info("Startup initialization failed!");
            return;
        }

        boolean osrEnabledArg = OS.isLinux();
        boolean transparentPaintingEnabledArg = true;
        boolean createImmediately = true;

        try {
            final MainFrame frame = new MainFrame(
                    osrEnabledArg, transparentPaintingEnabledArg, createImmediately, args);
            frame.setExtendedState(MAXIMIZED_BOTH);
            frame.setSize(800,400);
            frame.setTitle("Catan Portable Game");
        } catch (Throwable e) {}
    }

    public static void extract(ZipInputStream zip, File target) throws IOException {
        try {
            Log.info(String.valueOf(zip.available()));
            ZipEntry entry;

            while ((entry = zip.getNextEntry()) != null) {
                File file = new File(target, entry.getName());

                if (!file.toPath().normalize().startsWith(target.toPath())) {
                    throw new IOException("Bad zip entry");
                }

                if (entry.isDirectory()) {
                    file.mkdirs();
                    continue;
                }

                byte[] buffer = new byte[1024];
                file.getParentFile().mkdirs();
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
                int count;

                while ((count = zip.read(buffer)) != -1) {
                    out.write(buffer, 0, count);
                }

                out.close();
            }
        }
        catch (Exception e) {
            Log.error("Zip Folder Could Not Be UnZipped", e);
        }
        finally {
            zip.close();
        }
    }

    private static void loadWindows(Path tempBinDirectory, String s) {
        Log.info(tempBinDirectory + "\\bin\\" + s + ".dll");
        System.load(tempBinDirectory + "\\bin\\" + s + ".dll");
    }

    private static void loadMacos(Path tempBinDirectory, String s) {
        Log.info(tempBinDirectory + "\\bin\\" + s + ".dll");
        System.load(tempBinDirectory + "\\bin\\" + s + ".dll");
    }

    private static void loadLinux(Path tempBinDirectory, String s) {
        Log.info(tempBinDirectory + "\\bin\\" + s + ".dll");
        System.load(tempBinDirectory + "\\bin\\" + s + ".dll");
    }

    private boolean browserFocus = true;

    public MainFrame(boolean osrEnabled, boolean transparentPaintingEnabled,
            boolean createImmediately, String[] args) throws Error {

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
        getBrowser().createImmediately();

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                MainFrame.api.disposeServer();
                cefClient.dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }
}
