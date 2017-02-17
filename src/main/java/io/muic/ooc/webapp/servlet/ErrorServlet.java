package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.Webapp;
import io.muic.ooc.webapp.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorServlet extends HttpServlet{

    private SecurityService securityService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Webapp.mySQLConnection.getUsers();
        boolean authorized = securityService.isAuthorized(req);
        if (authorized) {
            resp.sendRedirect("/");
        } else {
            resp.sendRedirect("/login");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Webapp.mySQLConnection.getUsers();
        boolean authorized = securityService.isAuthorized(req);
        if (authorized) {
            resp.sendRedirect("/");
        } else {
            resp.sendRedirect("/login");
        }
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}