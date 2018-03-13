/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rieckpil.user.boundary;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author rieckpil
 */
@Named
@RequestScoped
public class CustomerBean {

    private List<Customer> customers;

    @PostConstruct
    public void init() {
        this.customers = new ArrayList<>();

        Customer c1 = new Customer("XYZ1337", "Mustermann", "Max");
        Customer c2 = new Customer("0815123", "Doe", "John");

        customers.add(c1);
        customers.add(c2);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

}
