package org.jboss.resteasy.examples.util;

import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyContextParameters;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

public class WebInit implements WebApplicationInitializer {
    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
        setupServlet(servletContext);
        setupFilter(servletContext);
    }

    private void setupServlet(final ServletContext servletContext) {
        final ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new HttpServletDispatcher());
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/*");
        dispatcher.setInitParameter(ResteasyContextParameters.RESTEASY_SCAN, "true");
    }

    private void setupFilter(final ServletContext servletContext) {
        final FilterRegistration filter = servletContext.addFilter("apiOriginFilter", ApiOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), true, "/*");
    }
}
