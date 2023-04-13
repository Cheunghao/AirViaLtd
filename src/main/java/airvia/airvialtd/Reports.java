package airvia.airvialtd;

/**
 * The Reports class provides methods for generating reports
 * Retrieve the necessary data and formats it into a report format.
 */
public class Reports {
    /**
     * Constructs a new Reports object with the specified report ID, payment type, flight type, commission rate, sale total,
     * currency ID, travel advisor ID, ticket ID, and card information.
     *
     * @param reportID the report ID
     * @param paymentType the payment type
     * @param flightType the flight type
     * @param commissionRate the commission rate
     * @param saleTotal the sale total
     * @param currencyID the currency ID
     * @param travelAdvisorID the travel advisor ID
     * @param ticketID the ticket ID
     * @param cardInfo the card information
     */
    public Reports(Integer reportID, String paymentType, String flightType, Float commissionRate, Float saleTotal, Integer currencyID, Integer travelAdvisorID, Integer ticketID, String cardInfo) {
        this.reportID = reportID; // Set the report ID
        this.currencyID = currencyID; // Set the currency ID
        this.travelAdvisorID = travelAdvisorID; // Set the travel advisor ID
        this.ticketID = ticketID; // Set the ticket ID
        this.paymentType = paymentType; // Set the payment type
        this.flightType = flightType; // Set the flight type
        this.commissionRate = commissionRate; // Set the commission rate
        this.saleTotal = saleTotal; // Set the sale total
        this.cardInfo = cardInfo; // Set the card information
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

    public String getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(String cardInfo) {
        this.cardInfo = cardInfo;
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
    private String paymentType, flightType, cardInfo;
    private Float commissionRate, saleTotal;
}
