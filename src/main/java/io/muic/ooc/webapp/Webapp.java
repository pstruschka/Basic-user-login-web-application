package io.muic.ooc.webapp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import io.muic.ooc.webapp.service.SecurityService;
import io.muic.ooc.webapp.util.Encryption;
import io.muic.ooc.webapp.util.MySQLConnection;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class Webapp {

    private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    private static final String MYSQL_URL = "jdbc:mysql://127.0.0.1/ooc?"
            + "user=p&password=threat-bull-middle";

    public static MySQLConnection mySQLConnection = new MySQLConnection(MYSQL_DRIVER, MYSQL_URL);

    public static void main(String[] args) throws LifecycleException,
            InterruptedException, ServletException {

        String docBase = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(80);
        SecurityService securityService = new SecurityService();

        ServletRouter servletRouter = new ServletRouter();
        servletRouter.setSecurityService(securityService);

        try {
            System.out.println(Encryption.createHash("pass"));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        mySQLConnection.getUsers();

        Context ctx;
        try {
            ctx = tomcat.addWebapp("/", new File(docBase).getAbsolutePath());
            servletRouter.init(ctx);
            tomcat.start();
            tomcat.getServer().await();
        } catch (ServletException | LifecycleException ex) {
            ex.printStackTrace();
        }
    }
}
