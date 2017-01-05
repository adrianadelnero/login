package br.com.authentication.integration.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceProviderResolver;
import javax.persistence.spi.PersistenceProviderResolverHolder;

import org.hibernate.jpa.HibernatePersistenceProvider;

/**
 *
 * @author aromano
 */
public class HibernatePersistenceProviderResolver implements PersistenceProviderResolver {
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
        PersistenceProviderResolverHolder.setPersistenceProviderResolver(new HibernatePersistenceProviderResolver());
    }
    
    protected PersistenceProvider buildPersistenceProvider() {
        return new HibernatePersistenceProvider();
    }

}
