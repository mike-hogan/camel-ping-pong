package mike.learning;


import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {

        Server server = new Server(8080);

        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[] {
                webapp("/ping", "camel-ping/target/camel-ping.war"),
                webapp("/pong", "camel-pong/target/camel-pong.war")
        });

        server.setHandler(contexts);

        server.start();

        server.join();

    }

    private static WebAppContext webapp(String contextPath, String warPath) {
        WebAppContext quoteCommandRest = new WebAppContext();
        quoteCommandRest.setContextPath(contextPath);
        File warFile = new File("../" + warPath);
        quoteCommandRest.setWar(warFile.getAbsolutePath());
        return quoteCommandRest;
    }
}
