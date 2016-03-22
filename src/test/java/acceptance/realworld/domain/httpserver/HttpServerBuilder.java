package acceptance.realworld.domain.httpserver;

import javax.servlet.http.HttpServlet;

public interface HttpServerBuilder {
    HttpServer build();
    HttpServerBuilder withServlet(HttpServlet servlet);
}
