package acceptance.realworld.userinterface;

import acceptance.realworld.application.PortingAuthorizationFlow;
import acceptance.realworld.userinterface.httpserver.HttpServer;
import acceptance.realworld.wiring.Wiring;

public class Application {

    private final Wiring wiring;

    public Application(Wiring wiring) {
        this.wiring = wiring;
    }

    public void start() {
        PortingAuthorizationFlow portingAuthorizationFlow = new PortingAuthorizationFlow(wiring.portingAuthorizationService(), wiring.repository());
        ExampleHttpServlet exampleHttpServlet = new ExampleHttpServlet(portingAuthorizationFlow);

        HttpServer httpServer = wiring.httpServerBuilder().withServlet(exampleHttpServlet).build();
        httpServer.start();
    }
}
