package org.itschoolhillel.dnepropetrovsk;

import org.itschoolhillel.dnepropetrovsk.datasource.EntitySource;
import org.itschoolhillel.dnepropetrovsk.datasource.sql.SQLEntitySource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by stephenvolf on 05/02/17.
 */
public class ContextListener implements ServletContextListener {
    public static final String ENTITY_SOURCE_KEY = "EntitySource";

    EntitySource entitySource;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        entitySource = new SQLEntitySource();
        servletContextEvent.getServletContext().setAttribute(ENTITY_SOURCE_KEY, entitySource);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        entitySource.close();
    }
}
