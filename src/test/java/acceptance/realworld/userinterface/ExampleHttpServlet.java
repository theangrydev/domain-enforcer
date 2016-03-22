package acceptance.realworld.userinterface;

import acceptance.realworld.application.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ExampleHttpServlet extends HttpServlet {

    private final ApplicationService applicationService;

    public ExampleHttpServlet(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        applicationService.doSomething();
        PrintWriter writer = response.getWriter();
        writer.print("Hello World!");
    }
}
