package com.example.config;

import com.example.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // root config
        AnnotationConfigWebApplicationContext objRootConfig = new AnnotationConfigWebApplicationContext();
        objRootConfig.register(RootConfig.class, DbConfig.class);
        objRootConfig.refresh();
        servletContext.addListener(new ContextLoaderListener(objRootConfig));

        // servlet config
        AnnotationConfigWebApplicationContext objRegister = new AnnotationConfigWebApplicationContext();
        objRegister.register(ServletConfig.class);
        ServletRegistration.Dynamic objRegistration =
                servletContext.addServlet("servlet", new DispatcherServlet(objRegister));

        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(Properties.TMP_FOLDER, Properties.MAX_UPLOAD_SIZE, Properties.MAX_UPLOAD_SIZE * 2, Properties.MAX_UPLOAD_SIZE / 2);
        objRegistration.setMultipartConfig(multipartConfigElement);

        objRegistration.setLoadOnStartup(1);
        objRegistration.addMapping("/");
    }
}