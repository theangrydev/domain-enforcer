/*
 * Copyright 2016 Liam Williams <liam.williams@zoho.com>.
 *
 * This file is part of domain-enforcer.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package acceptance.realworld.userinterface;

import acceptance.realworld.application.PortingAuthorizationFlow;
import acceptance.realworld.domain.Widget;
import acceptance.realworld.domain.portingauthorization.FooResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ExampleHttpServlet extends HttpServlet {

    private final PortingAuthorizationFlow portingAuthorizationFlow;

    public ExampleHttpServlet(PortingAuthorizationFlow portingAuthorizationFlow) {
        this.portingAuthorizationFlow = portingAuthorizationFlow;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String misdn = request.getParameter("MISDN");
        FooResult result = portingAuthorizationFlow.requestPortingAuthorizationCode(new Widget(misdn));

        PrintWriter writer = response.getWriter();
        result.errorCode().ifPresent(errorCode -> writer.print("Error: " + errorCode));
        result.portingAuthorizationCode().ifPresent(pacCode -> writer.print("Code: " + pacCode));
    }
}
