/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rieckpil.event.sourcing.moneytransfer;

/**
 *
 * @author Philip
 */
public class FundTransferDTO {
    
    private AccountType sourceAcctType;
    private Long sourceAcctNbr;
    private AccountType destAcctType;
    private Long destAcctNbr;
    private double amt;

    public AccountType getSourceAcctType() {
        return sourceAcctType;
    }

    public void setSourceAcctType(AccountType sourceAcctType) {
        this.sourceAcctType = sourceAcctType;
    }

    public Long getSourceAcctNbr() {
        return sourceAcctNbr;
    }

    public void setSourceAcctNbr(Long sourceAcctNbr) {
        this.sourceAcctNbr = sourceAcctNbr;
    }

    public AccountType getDestAcctType() {
        return destAcctType;
    }

    public void setDestAcctType(AccountType destAcctType) {
        this.destAcctType = destAcctType;
    }

    public Long getDestAcctNbr() {
        return destAcctNbr;
    }

    public void setDestAcctNbr(Long destAcctNbr) {
        this.destAcctNbr = destAcctNbr;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }
    
}
