package com.example.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfig.class);
        rootContext.refresh();
        servletContext.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext servletConfig = new AnnotationConfigWebApplicationContext();
        servletConfig.register(ServletConfig.class);
        ServletRegistration.Dynamic registration = servletContext.addServlet("servlet", new DispatcherServlet(servletConfig));

        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
