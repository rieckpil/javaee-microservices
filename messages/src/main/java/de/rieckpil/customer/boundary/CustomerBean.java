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
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @Inject
    private Event<Customer> customerCreatedEvent;

    private List<Customer> customers;

    @PostConstruct
    public void init() {
        customers = new ArrayList<>();
    }

    public void loadCustomers() {
        this.customers = customerRepository.findAll();
    }

    public void createCustomer() {
        System.out.println(customerNumber + " " + firstName + " " + lastName);
        Customer customer = new Customer(customerNumber, firstName, lastName);
        customerRepository.persist(customer);
        customerCreatedEvent.fire(customer);
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

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

}
