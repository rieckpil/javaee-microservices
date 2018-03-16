/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rieckpil.products.boundary;

import de.rieckpil.products.entity.Product;
import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author rieckpil
 */
@Dependent
@Named
public class StringToProductProcessor implements ItemProcessor {

    @Override
    public Object processItem(Object item) throws Exception {
        String csvLine = (String) item;
        Product product = new Product(csvLine);
        return product;
    }
    
}
