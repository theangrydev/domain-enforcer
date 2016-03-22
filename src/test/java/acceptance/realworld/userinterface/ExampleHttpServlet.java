package acceptance.realworld.userinterface;

import acceptance.realworld.application.ApplicationService;
import acceptance.realworld.domain.MSISDN;
import acceptance.realworld.domain.pac.PortingAuthorizationResult;

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
        String misdn = request.getParameter("MISDN");
        PortingAuthorizationResult result = applicationService.requestPortingAuthorizationCode(new MSISDN(misdn));

        PrintWriter writer = response.getWriter();
        result.errorCode().ifPresent(errorCode -> writer.print("Error: " + errorCode));
        result.portingAuthorizationCode().ifPresent(pacCode -> writer.print("Code: " + pacCode));
    }
}
