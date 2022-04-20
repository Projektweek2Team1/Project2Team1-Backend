package com.example.demo.database.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Opens connection when application is starting
 */
@WebListener
public class AppContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String url = System.getenv("DB_URL");
        String schema = System.getenv("DB_SCHEMA");
        DBConnectionService.connect(url, schema);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DBConnectionService.disconnect();
    }
}