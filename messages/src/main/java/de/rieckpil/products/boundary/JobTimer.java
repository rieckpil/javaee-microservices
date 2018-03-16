/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rieckpil.products.boundary;

import java.util.Properties;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

/**
 *
 * @author rieckpil
 */
@Singleton
public class JobTimer {
    
    @Schedule(second="30")
    public void startJob() {
        System.out.println("Starting Batch Job for importing products");
        JobOperator operator = BatchRuntime.getJobOperator();
        long executionId = operator.start("productImport", new Properties());
    }
}
