package airvia.airvialtd;

import javafx.beans.property.*;

import java.sql.*;
import java.util.ArrayList;

public class Tickets {


    private Integer ticketID, currencyID, blankID;

    private String validityStatus, purchaseDate, paymentType, refundStatus;
    private Float paymentTotal;

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Integer getBlankID() {
        return blankID;
    }

    public void setBlankID(Integer blankID) {
        this.blankID = blankID;
    }

    public Integer getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(Integer currencyID) {
        this.currencyID = currencyID;
    }

    public Float getPaymentTotal() {
        return paymentTotal;
    }

    public void setPaymentTotal(Float paymentTotal) {
        this.paymentTotal = paymentTotal;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getTicketID() {
        return ticketID;
    }

    public void setTicketID(Integer ticketID) {
        this.ticketID = ticketID;
    }


    public String getValidityStatus() {
        return validityStatus;
    }

    public void setValidityStatus(String validityStatus) {
        this.validityStatus = validityStatus;
    }


    public Tickets(Integer ticketID, String validityStatus, String purchaseDate, String paymentType, Float paymentTotal, Integer currencyID, Integer blankID, String refundStatus) {
        this.ticketID = ticketID;
        this.validityStatus = validityStatus;
        this.purchaseDate = purchaseDate;
        this.paymentType = paymentType;
        this.paymentTotal = paymentTotal;
        this.currencyID = currencyID;
        this.blankID = blankID;
        this.refundStatus = refundStatus;
    }

}