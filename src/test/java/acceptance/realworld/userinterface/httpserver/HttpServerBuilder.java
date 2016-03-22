package acceptance.realworld.userinterface.httpserver;

import javax.servlet.http.HttpServlet;

public interface HttpServerBuilder {
    HttpServer build();
    HttpServerBuilder withServlet(HttpServlet servlet);
}
