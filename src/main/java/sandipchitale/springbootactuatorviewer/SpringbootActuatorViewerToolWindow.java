package sandipchitale.springbootactuatorviewer;

import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.ui.jcef.JBCefBrowser;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.file.Files;

public class SpringbootActuatorViewerToolWindow {

    private final JPanel contentToolWindow;

    public SpringbootActuatorViewerToolWindow(Project project) {
        this.contentToolWindow = new SimpleToolWindowPanel(true, true);

//        JPanel toolBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        toolBar.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 2));
//
//        JButton refreshButton = new JButton(AllIcons.Actions.Refresh);
//        refreshButton.setToolTipText("Reload");
//        refreshButton.addActionListener(e -> {
//        });
//
//        toolBar.add(refreshButton);
//        this.contentToolWindow.add(toolBar, BorderLayout.NORTH);

        JBCefBrowser browser = new JBCefBrowser("about:blank");
        contentToolWindow.add(browser.getComponent(), BorderLayout.CENTER);

        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(0), 0);
            httpServer.createContext("/", new RootHandler());
            httpServer.setExecutor(null); // creates a default executor
            httpServer.start();

            browser.loadURL("http://localhost:" + httpServer.getAddress().getPort() + "/index.html");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JComponent getContent() {
        return this.contentToolWindow;
    }

    static class RootHandler implements HttpHandler {

        public static final String BASEDIR =
                PluginManagerCore.getPlugin(PluginId.getId("sandipchitale.springboot-actuator-viewer")).getPath().getAbsolutePath() + File.separator + "app";

        @Override
        public void handle(HttpExchange ex) throws IOException {
            URI uri = ex.getRequestURI();
            String name = new File(uri.getPath()).getName();
            File path = new File(BASEDIR, name);

            Headers h = ex.getResponseHeaders();
            // Could be more clever about the content type based on the filename here.
            if (name.endsWith(".html")) {
                h.add("Content-Type", "text/html");
            } else if (name.endsWith(".js")) {
                h.add("Content-Type", "text/javascript");
            } else if (name.endsWith(".css")) {
                h.add("Content-Type", "text/css");
            }

            OutputStream out = ex.getResponseBody();

            if (path.exists()) {
                ex.sendResponseHeaders(200, path.length());
                out.write(Files.readAllBytes(path.toPath()));
            } else {
                System.err.println("File not found: " + path.getAbsolutePath());

                ex.sendResponseHeaders(404, 0);
                out.write("404 File not found.".getBytes());
            }

            out.close();
        }
    }
}