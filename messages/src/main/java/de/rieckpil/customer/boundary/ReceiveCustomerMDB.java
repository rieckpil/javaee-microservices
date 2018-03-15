/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rieckpil.customer.boundary;

import de.rieckpil.customer.entity.Customer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author rieckpil
 */
@MessageDriven(mappedName="jms/queue/CustomerQueue", activationConfig = {
    @ActivationConfigProperty (
            propertyName = "destinationType",
            propertyValue = "javax.jms.Queue"
    )
})
public class ReceiveCustomerMDB implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            Customer customer = message.getBody(Customer.class);
            System.out.println("Incoming customer for sending his invoice: " + customer.toString());
        } catch (JMSException ex) {
            Logger.getLogger(ReceiveCustomerMDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
