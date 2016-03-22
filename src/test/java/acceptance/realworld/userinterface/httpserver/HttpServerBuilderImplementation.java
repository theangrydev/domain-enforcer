package acceptance.realworld.userinterface.httpserver;

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
