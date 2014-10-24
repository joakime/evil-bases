package org.eclipse.jetty.demo;

import java.net.URL;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.JettyWebXmlConfiguration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;

public class LaunchWebApp
{
    public static void main(String[] args) throws Throwable
    {
        int port = 9090;
        Server server = new Server(port);

        WebAppContext context = new WebAppContext();
        context.setResourceBase("src/main/webapp");
        context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
        
        // We need a full url and/or path here, lets look it up
        String keyResource = DumperServlet.class.getName().replace(".","/")+".class";
        URL keyResourceUrl = Thread.currentThread().getContextClassLoader().getResource(keyResource);
        String keyResourceUrlStr = keyResourceUrl.toExternalForm();
        String baseResourceUrl = keyResourceUrlStr.substring(0, keyResourceUrlStr.length() - keyResource.length());
        
        System.err.println("Resource URL is " + keyResourceUrl);
        System.err.println("Base URL is " + baseResourceUrl);
        
        context.setExtraClasspath(baseResourceUrl);
        context.setConfigurations(new Configuration[]
        { 
            new AnnotationConfiguration(), 
            new WebInfConfiguration(), 
            new WebXmlConfiguration(), 
            new MetaInfConfiguration(), 
            new FragmentConfiguration(),
            new EnvConfiguration(), 
            new PlusConfiguration(), 
            new JettyWebXmlConfiguration() 
        });

        context.setContextPath("/");
        context.setParentLoaderPriority(true);
        server.setHandler(context);
        server.start();
        server.dump(System.err);
        server.join();
    }
}
