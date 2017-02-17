package io.muic.ooc.webapp;

import io.muic.ooc.webapp.service.SecurityService;
import io.muic.ooc.webapp.servlet.*;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ErrorPage;

public class ServletRouter {
    private SecurityService securityService;

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public void init(Context ctx) {
        initHome(ctx);
        initLogin(ctx);
        initError(ctx);
        initAdd(ctx);
        initEdit(ctx);
        initConfirm(ctx);
    }

    private void initHome(Context ctx) {
        HomeServlet homeServlet = new HomeServlet();
        homeServlet.setSecurityService(securityService);
        Tomcat.addServlet(ctx, "HomeServlet", homeServlet);
        ctx.addServletMapping("/index.jsp", "HomeServlet");
    }

    private void initLogin(Context ctx) {
        LoginServlet loginServlet = new LoginServlet();
        loginServlet.setSecurityService(securityService);
        Tomcat.addServlet(ctx, "LoginServlet", loginServlet);
        ctx.addServletMapping("/login", "LoginServlet");
    }

    private void initError(Context ctx) {
        ErrorServlet errorServlet = new ErrorServlet();
        errorServlet.setSecurityService(securityService);
        Tomcat.addServlet(ctx, "ErrorServlet", errorServlet);
        ctx.addServletMapping("/error", "ErrorServlet");
        ErrorPage errorPage = new ErrorPage();
        errorPage.setErrorCode(404);
        errorPage.setLocation("/error");
        System.out.println("badpage");
        ctx.addErrorPage(errorPage);
    }

    private void initAdd(Context ctx) {
        AddServlet addServlet = new AddServlet();
        addServlet.setSecurityService(securityService);
        Tomcat.addServlet(ctx, "AddServlet", addServlet);
        ctx.addServletMapping("/add", "AddServlet");
    }

    private void initEdit(Context ctx) {
        EditServlet editServlet = new EditServlet();
        editServlet.setSecurityService(securityService);
        Tomcat.addServlet(ctx, "EditServlet", editServlet);
        ctx.addServletMapping("/edit", "EditServlet");
    }

    private void initConfirm(Context ctx) {
        ConfirmServlet confirmServlet = new ConfirmServlet();
        confirmServlet.setSecurityService(securityService);
        Tomcat.addServlet(ctx, "ConfirmServlet", confirmServlet);
        ctx.addServletMapping("/confirm", "ConfirmServlet");
    }
}
