/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.authentication.presentation.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.authentication.integration.dao.HibernatePersistenceProviderResolver;

/**
 *
 * @author aromano
 */
public class HibernatePersistenceProviderRegisterListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        registerHibernatePersistenceProvider();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //does nothing
    }

    protected void registerHibernatePersistenceProvider() {
        HibernatePersistenceProviderResolver.register();
    }
    
}
