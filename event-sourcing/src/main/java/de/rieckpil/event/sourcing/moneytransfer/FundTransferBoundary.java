/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rieckpil.event.sourcing.moneytransfer;

import fish.payara.cloud.connectors.kafka.api.KafkaConnection;
import fish.payara.cloud.connectors.kafka.api.KafkaConnectionFactory;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.kafka.clients.producer.ProducerRecord;

@Named
@ViewScoped
public class FundTransferBoundary implements Serializable {

    @Resource(lookup = "java:module/env/KafkaConnectionFactory")
    private KafkaConnectionFactory kafkaConnectionFactory;

    @Inject
    private FacesContext facesContext;

    private static final Logger LOGGER
            = Logger.getLogger(FundTransferBoundary.class.getName());

    public void transferFunds() {
        
        LOGGER.log(Level.INFO, String.format("{0}.transferFunds() invoked",
                this.getClass().getClass()
        ));

        FundTransferDTO fundTransferDTO = new FundTransferDTO();
        fundTransferDTO.setSourceAcctType(AccountType.CHECKING);
        fundTransferDTO.setSourceAcctNbr(1234L);
        fundTransferDTO.setDestAcctType(AccountType.SAVINGS);
        fundTransferDTO.setDestAcctNbr(1123L);
        fundTransferDTO.setAmt(100.00);
        sendCheckingMessage(fundTransferDTO);
        facesContext.addMessage("msgs",
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Transfer funds message sent successfully",
                        "Transfer funds message sent successfully"));
    }

    public void simulateTransactionError() {
        LOGGER.log(Level.INFO,
                String.format("{0}.simulateTransactionError() invoked",
                        this.getClass().getClass()));
        FundTransferDTO fundTransferDTO = new FundTransferDTO();
        fundTransferDTO.setSourceAcctType(AccountType.CHECKING);
        fundTransferDTO.setSourceAcctNbr(1234L); //checking account
        fundTransferDTO
                .setDestAcctType(AccountType.SAVINGS);
        //invalid savings account number, will trigger sending
        //a compensating transaction to "rollback" the checking 
        fundTransferDTO
                .setDestAcctNbr(2222L);
        fundTransferDTO.setAmt(100.00); //amount to transfer
        sendCheckingMessage(fundTransferDTO);
        facesContext.addMessage("msgs",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Message simulating transaction error sent successfully. ", "Message simulating transaction error sent successfully. "));
    }

    private void sendCheckingMessage(FundTransferDTO fundTransferDTO) {

        String fundTransferDTOJson;
        
        fundTransferDTOJson
                = FundTransferDTOUtil.fundTransferDTOToJson(fundTransferDTO);

        try (KafkaConnection kafkaConnection
                = kafkaConnectionFactory.createConnection()) {
            
            kafkaConnection.send(new ProducerRecord("checkingaccttopic",
                    fundTransferDTOJson));

        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
}
