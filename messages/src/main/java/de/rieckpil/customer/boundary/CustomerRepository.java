package de.rieckpil.customer.boundary;

import de.rieckpil.customer.entity.Customer;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rieckpil
 */
@ApplicationScoped
public class CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Customer> findAll() {
        return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    public List<Customer> findByCustomerNumber(Long customerNumber) {
        return em.createQuery("SELECT c FROM Customer c WHERE c.customerNumber = :no", Customer.class).setParameter("no", customerNumber).getResultList();
    }

    public Customer persist(Customer customer) {
        return customer;
    }

    public Customer update(Customer customer) {
        return customer;
    }

    public void delete(Customer customer) {

    }
}
