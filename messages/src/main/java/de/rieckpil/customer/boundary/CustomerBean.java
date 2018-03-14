/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rieckpil.customer.boundary;

import de.rieckpil.customer.entity.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author rieckpil
 */
@Named
@RequestScoped
public class CustomerBean {

    @Inject
    private CustomerRepository customerRepository;

    @NotNull
    @Min(1)
    @Max(1000)
    private Long customerNumber;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @PostConstruct
    public void init() {

    }

    public void createCustomer() {
        customerRepository.persist(new Customer(customerNumber, firstName, lastName));
    }

    public Long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}
