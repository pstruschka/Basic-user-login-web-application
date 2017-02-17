package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.User;
import io.muic.ooc.webapp.Webapp;
import io.muic.ooc.webapp.service.SecurityService;
import io.muic.ooc.webapp.util.Encryption;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;

public class EditServlet extends HttpServlet{
    private SecurityService securityService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Webapp.mySQLConnection.getUsers();
        boolean authorized = securityService.isAuthorized(req);
        if (authorized) {
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/edit.jsp");
            rd.include(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Add");
        String action = req.getParameter("action");
        String username = req.getParameter("username");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        int id = Integer.parseInt(req.getParameter("id"));
        if (Objects.equals(action, "Cancel")) {
            resp.sendRedirect("/");
        } else if (StringUtils.isEmpty(username) || StringUtils.isEmpty(firstname) || StringUtils.isEmpty(lastname)) {
            String error = "Missing information";
            req.setAttribute("error", error);
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/edit.jsp");
            rd.include(req, resp);
        }
        else {
            Webapp.mySQLConnection.updateUser(id, username, firstname, lastname);
            resp.sendRedirect("/");
        }

    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

}
