package acceptance.realworld.infrastructure.httpserver;

import acceptance.realworld.domain.httpserver.HttpServer;
import acceptance.realworld.domain.httpserver.HttpServerBuilder;

import javax.servlet.http.HttpServlet;

public class HttpServerBuilderImplementation implements HttpServerBuilder {
    @Override
    public HttpServer build() {
        return new HttpServerImplementation();
    }

    @Override
    public HttpServerBuilder withServlet(HttpServlet servlet) {
        // use your imagination
        return this;
    }
}
