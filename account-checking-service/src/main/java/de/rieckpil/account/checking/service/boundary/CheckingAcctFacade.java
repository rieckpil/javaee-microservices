/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rieckpil.account.checking.service.boundary;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CheckingAcctFacade extends AbstractFacade<CheckingAcct> {

    @PersistenceContext(unitName = "fish.payara.eventsourcing_savings-acct-service_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CheckingAcctFacade() {
        super(CheckingAcct.class);
    }

    public CheckingAcct findByAcctNbr(Long acctNbr) {
        TypedQuery<CheckingAcct> typedQuery = em.createNamedQuery("CheckingAcct.findByAcctNbr", CheckingAcct.class);
        typedQuery.setParameter("acctNbr", acctNbr);

        return typedQuery.getSingleResult();
    }

}