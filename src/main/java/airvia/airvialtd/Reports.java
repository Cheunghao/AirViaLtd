package airvia.airvialtd;

public class Reports {

    public Reports(Integer reportID, String paymentType, String flightType, Float commissionRate, Float saleTotal, Integer currencyID, Integer travelAdvisorID, Integer ticketID) {
        this.reportID = reportID;
        this.currencyID = currencyID;
        this.travelAdvisorID = travelAdvisorID;
        this.ticketID = ticketID;
        this.paymentType = paymentType;
        this.flightType = flightType;
        this.commissionRate = commissionRate;
        this.saleTotal = saleTotal;
    }

    public Integer getReportID() {
        return reportID;
    }

    public void setReportID(Integer reportID) {
        this.reportID = reportID;
    }

    public Integer getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(Integer currencyID) {
        this.currencyID = currencyID;
    }

    public Integer getTravelAdvisorID() {
        return travelAdvisorID;
    }

    public void setTravelAdvisorID(Integer travelAdvisorID) {
        this.travelAdvisorID = travelAdvisorID;
    }

    public Integer getTicketID() {
        return ticketID;
    }

    public void setTicketID(Integer ticketID) {
        this.ticketID = ticketID;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getFlightType() {
        return flightType;
    }

    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    public Float getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(Float commissionRate) {
        this.commissionRate = commissionRate;
    }

    public Float getSaleTotal() {
        return saleTotal;
    }

    public void setSaleTotal(Float saleTotal) {
        this.saleTotal = saleTotal;
    }

    private Integer reportID, currencyID, travelAdvisorID, ticketID;
    private String paymentType, flightType;
    private Float commissionRate, saleTotal;
}
