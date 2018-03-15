/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rieckpil.customer.boundary;

import de.rieckpil.customer.entity.Customer;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 *
 * @author rieckpil
 */
@ApplicationScoped
public class MailService {

    @Resource(mappedName = "jms/queue/CustomerQueue")
    private Queue newCustomerQueue;

    @Inject
    private JMSContext context;

    public void sendMail(@Observes Customer newCustomer) {
        System.out.println("Sending Mail to new customer: " + newCustomer.getCustomerNumber());
        context.createProducer().send(newCustomerQueue, newCustomer);
    }
}
