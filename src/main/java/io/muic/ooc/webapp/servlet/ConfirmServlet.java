package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.Webapp;
import io.muic.ooc.webapp.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class ConfirmServlet extends HttpServlet{
    private SecurityService securityService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Webapp.mySQLConnection.getUsers();
        boolean authorized = securityService.isAuthorized(req);
        if (authorized) {
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/confirm.jsp");
            rd.include(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int targetId = Integer.parseInt(req.getParameter("id"));
        if (Objects.equals(action, "Yes")) {
            System.out.println("DELETE CONFIRMED");
            Webapp.mySQLConnection.removeUser(targetId);
        }
        resp.sendRedirect("/");
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
