package de.rieckpil.customer.control;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class CustomerCache {

    @PostConstruct
    private void loadStorage() {
        System.out.println("### loading customer data into cache");
    }
    @PreDestroy
    private void writeStorage() {
        System.out.println("### loading cache to file system");
    }

}
