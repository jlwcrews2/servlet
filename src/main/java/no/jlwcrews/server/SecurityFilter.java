package no.jlwcrews.server;


import jakarta.servlet.*;

import java.io.IOException;

public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(req, resp);
    }
}
