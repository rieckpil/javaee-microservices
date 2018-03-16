/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rieckpil.products.boundary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import javax.batch.api.chunk.ItemReader;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author rieckpil
 */
@Dependent
@Named
public class FlatFileReader implements ItemReader {
    
    private BufferedReader productCsvReader;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        productCsvReader = new BufferedReader(new FileReader("products.csv"));
    }

    @Override
    public void close() throws Exception {
        productCsvReader.close();
    }

    @Override
    public Object readItem() throws Exception {
        return productCsvReader.readLine();
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }
    
}
