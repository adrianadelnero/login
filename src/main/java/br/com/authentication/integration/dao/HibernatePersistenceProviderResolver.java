/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.authentication.integration.dao;

import java.util.Collections;
import java.util.List;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceProviderResolver;
import javax.persistence.spi.PersistenceProviderResolverHolder;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author aromano
 */
public class HibernatePersistenceProviderResolver implements PersistenceProviderResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernatePersistenceProviderResolver.class);
    private volatile PersistenceProvider persistenceProvider = buildPersistenceProvider();
    
    @Override
    public List<PersistenceProvider> getPersistenceProviders() {
        return Collections.singletonList(persistenceProvider);
    }

    @Override
    public void clearCachedProviders() {
        persistenceProvider = buildPersistenceProvider();
    }
    
    public static void register() {
        LOGGER.info("Registering HibernatePersistenceProviderResolver");
        PersistenceProviderResolverHolder.setPersistenceProviderResolver(new HibernatePersistenceProviderResolver());
    }
    
    protected PersistenceProvider buildPersistenceProvider() {
        return new HibernatePersistenceProvider();
    }

}
