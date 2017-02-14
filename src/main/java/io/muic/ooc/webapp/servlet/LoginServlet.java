package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.service.SecurityService;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "LoginServlet",
        urlPatterns = {"/login"}
)
public class LoginServlet extends HttpServlet {

    private SecurityService securityService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String nextJSP = "WEB-INF/login.jsp";

        System.out.println(nextJSP);


        req.getRequestDispatcher(nextJSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("Login");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {
            if (securityService.authenticate(username, password, req)) {
                resp.sendRedirect("/");
            } else {
                String error = "Wrong username or password.";
                req.setAttribute("error", error);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/login.jsp");
                rd.include(req, resp);
            }
        } else {
            String error = "Username or password is missing.";
            req.setAttribute("error", error);
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/login.jsp");
            rd.include(req, resp);
        }
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

}