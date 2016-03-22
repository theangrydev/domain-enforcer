package acceptance.realworld.infrastructure.httpserver;

import acceptance.realworld.userinterface.httpserver.HttpServer;

public class HttpServerImplementation implements HttpServer {
    @Override
    public void start() {
        System.out.println("Starting httpserver...");
    }
}
