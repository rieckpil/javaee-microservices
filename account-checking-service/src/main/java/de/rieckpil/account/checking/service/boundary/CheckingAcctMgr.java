/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rieckpil.account.checking.service.boundary;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 *
 * @author Philip
 */
@RequestScoped
@Transactional
public class CheckingAcctMgr implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(CheckingAcctMgr.class.getName());

    @Inject
    private CheckingAcctFacade checkingAcctFacade;

    public boolean withdrawFunds(FundTransferDTO fundTransferDTO) {
        boolean success;
        CheckingAcct checkingAcct = checkingAcctFacade.findByAcctNbr(fundTransferDTO.getSourceAcctNbr());

        if (fundTransferDTO.getAmt() <= checkingAcct.getAcctBalance()) {
            checkingAcct.setAcctBalance(checkingAcct.getAcctBalance() - fundTransferDTO.getAmt());
            checkingAcctFacade.edit(checkingAcct);
            success = true;
        } else {
            LOGGER.log(Level.WARNING, "Insufficient funds in checking account");
            success = false;
        }

        return success;
    }

    public void depositFunds(FundTransferDTO fundTransferDTO) {
        CheckingAcct checkingAcct = checkingAcctFacade.findByAcctNbr(fundTransferDTO.getDestAcctNbr());

        checkingAcct.setAcctBalance(checkingAcct.getAcctBalance() + fundTransferDTO.getAmt());
        checkingAcctFacade.edit(checkingAcct);
    }
}
