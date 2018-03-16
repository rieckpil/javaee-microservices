/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rieckpil.products.boundary;

import java.io.Serializable;
import java.util.List;
import javax.batch.api.chunk.ItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rieckpil
 */
@Dependent
@Named
public class ProductWriter implements ItemWriter {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public void open(Serializable checkpoint) throws Exception {
    }

    @Override
    public void close() throws Exception {
    }

    @Override
    public void writeItems(List<Object> items) throws Exception {
        for (Object item : items) {
            entityManager.persist(item);
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }
    
}
