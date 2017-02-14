package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class HomeServlet extends HttpServlet {

    private SecurityService securityService;

    public void setSecurityManager(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(req);
        if (authorized) {
            // do MVC in here
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/home.jsp");
            rd.include(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Objects.equals(req.getParameter("action"), "logout")) {
            securityService.logout(req);
            String error = "Logged out.";
            System.out.println("LOGOUT: " + error);
            req.setAttribute("error", error);
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/login.jsp");
            rd.include(req, resp);
        }
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

}
