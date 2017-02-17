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

public class HomeServlet extends HttpServlet {

    private SecurityService securityService;
    public static Integer targetId = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Webapp.mySQLConnection.getUsers();
        boolean authorized = securityService.isAuthorized(req);
        if (authorized) {
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/home.jsp");
            rd.include(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (Objects.equals(action, "Logout")) {
            req.removeAttribute("action");
            securityService.logout(req);
            String error = "Logged out.";
            System.out.println("LOGOUT: " + error);
            req.setAttribute("error", error);
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/login.jsp");
            rd.include(req, resp);
            return;
        } else if (Objects.equals(action, "Add")) {
            System.out.println("add");
            resp.sendRedirect("/add");
            return;
        } else if (Objects.equals(action, "Edit")) {
            HomeServlet.targetId = Integer.parseInt(req.getParameter("id"));
            resp.sendRedirect("/edit");
            System.out.println("edit");
            return;
        } else if (Objects.equals(action, "Remove")) {
            HomeServlet.targetId = Integer.parseInt(req.getParameter("id"));
            resp.sendRedirect("/confirm");
            System.out.println("remove");
            return;
        }
        System.out.println(req.getParameter("id"));
        req.removeAttribute("id");
        req.removeAttribute("action");
        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/home.jsp");
        rd.include(req, resp);

    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

}
