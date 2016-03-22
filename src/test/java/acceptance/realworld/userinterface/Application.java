package acceptance.realworld.userinterface;

import acceptance.realworld.application.ApplicationService;
import acceptance.realworld.userinterface.httpserver.HttpServer;
import acceptance.realworld.wiring.Wiring;

public class Application {

    private final Wiring wiring;

    public Application(Wiring wiring) {
        this.wiring = wiring;
    }

    public void start() {
        ApplicationService applicationService = new ApplicationService(wiring.portingAuthorizationService(), wiring.repository());
        ExampleHttpServlet exampleHttpServlet = new ExampleHttpServlet(applicationService);

        HttpServer httpServer = wiring.httpServerBuilder().withServlet(exampleHttpServlet).build();
        httpServer.start();
    }
}
