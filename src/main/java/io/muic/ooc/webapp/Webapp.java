package io.muic.ooc.webapp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class Webapp {
    public static void main(String[] args) throws LifecycleException,
            InterruptedException, ServletException {
        /*
        String docBase = "src/main/webapp/";
        String contextPath = "";
        String appBase = "src/main/webapp";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8082);
        //tomcat.getHost().setAppBase(appBase);
        tomcat.addWebapp(contextPath, new File(appBase).getAbsolutePath());

        //tomcat.addWebapp("", new File(docBase).getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();
        */

        String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        User admin = new User("admin", "pass");

        tomcat.start();
        tomcat.getServer().await();
    }
}
