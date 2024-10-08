package no.jlwcrews.server;


import jakarta.servlet.DispatcherType;
import jakarta.servlet.RequestDispatcher;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;

import java.util.EnumSet;

public class CustomerServer {

    Server server = new Server(8181);

    public void start() throws Exception {
        Resource resource = Resource.newClassPathResource("/webapp");
        var webApp = new WebAppContext(resource, "/");
        webApp.addServlet(new ServletHolder(new CustomerServlet()), "/api/customer");
        webApp.addServlet(new ServletHolder(new DatabaseServlet()), "/api/database");
        webApp.addFilter(new FilterHolder(new SecurityFilter()), "/*", EnumSet.of(DispatcherType.REQUEST));
        server.setHandler(webApp);
        server.start();
    }

}
