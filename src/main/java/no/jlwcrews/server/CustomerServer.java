package no.jlwcrews.server;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;

public class CustomerServer {

    Server server = new Server(8181);

    public void start() throws Exception {
        Resource resource = Resource.newClassPathResource("/webapp");
        var webApp = new WebAppContext(resource, "/");
        webApp.addServlet(new ServletHolder(new CustomerServlet()), "/api/customer");
        webApp.addServlet(new ServletHolder(new DatabaseServlet()), "/api/database");

        server.setHandler(webApp);
        server.start();
    }

}
