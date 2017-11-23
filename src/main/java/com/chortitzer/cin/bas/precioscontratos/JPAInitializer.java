package com.chortitzer.cin.bas.precioscontratos;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistService;


@Singleton
public class JPAInitializer {
    @Inject
    JPAInitializer(PersistService persistenceService) {
        // start JPA
        persistenceService.start();
    }
}
