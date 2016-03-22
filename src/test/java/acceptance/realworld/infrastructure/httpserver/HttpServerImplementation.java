package acceptance.realworld.infrastructure.httpserver;

import acceptance.realworld.domain.httpserver.HttpServer;

public class HttpServerImplementation implements HttpServer {
    @Override
    public void start() {
        System.out.println("Starting httpserver...");
    }
}
