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

public class AddServlet extends HttpServlet{
    private SecurityService securityService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Webapp.mySQLConnection.getUsers();
        boolean authorized = securityService.isAuthorized(req);
        if (authorized) {
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/add.jsp");
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
        String password = req.getParameter("password");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        if (Objects.equals(action, "Cancel")) {
            resp.sendRedirect("/");
        } else if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(firstname) || StringUtils.isEmpty(lastname)) {
            String error = "Missing information";
            req.setAttribute("error", error);
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/add.jsp");
            rd.include(req, resp);
        } else if (!User.isNewUserName(username)) {
            String error = "User Exists";
            req.setAttribute("error", error);
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/add.jsp");
            rd.include(req, resp);
        }
        else {
            String hashedPass = null;
            try {
                hashedPass = Encryption.createHash(password);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
            }
            if (hashedPass == null) {
                String error = "Bad password";
                req.setAttribute("error", error);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/add.jsp");
                rd.include(req, resp);
                return;
            }
            String error;
            if (Webapp.mySQLConnection.addUser(username, hashedPass, firstname, lastname)) {
                error = "User Created";
            } else {
                error = "user Not Created";
            }
            req.setAttribute("error", error);
            resp.sendRedirect("/");
        }
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

}
