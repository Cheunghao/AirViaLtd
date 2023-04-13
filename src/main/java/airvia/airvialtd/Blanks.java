package airvia.airvialtd;

/**
 * The Blanks class represents the Blank Object
 */
public class Blanks {

    /**
     * Constructor for the Blanks class.
     *
     * @param blankID           The ID of the blank.
     * @param blankType         The type of the blank.
     * @param blankNumber       The number of the blank.
     * @param airlineName       The name of the airline.
     * @param auditorsCoupons   The number of auditor's coupons.
     * @param flightCoupon      The number of flight coupons.
     * @param travelAdvisorID   The ID of the travel advisor.
     * @param sysadminID        The ID of the sysadmin.
     */
    public Blanks(Integer blankID, Integer blankType, Long blankNumber, String airlineName,
                  Integer auditorsCoupons, Integer flightCoupon, Integer travelAdvisorID, Integer sysadminID) {
        this.blankID = blankID;
        this.blankType = blankType;
        this.blankNumber = blankNumber;
        this.auditorsCoupons = auditorsCoupons;
        this.flightCoupon = flightCoupon;
        this.travelAdvisorID = travelAdvisorID;
        this.sysadminID = sysadminID;
        this.airlineName = airlineName;
    }


    public Integer getBlankID() {
        return blankID;
    }

    public void setBlankID(Integer blankID) {
        this.blankID = blankID;
    }

    public Integer getBlankType() {
        return blankType;
    }

    public void setBlankType(Integer blankType) {
        this.blankType = blankType;
    }

    public Long getBlankNumber() {
        return blankNumber;
    }

    public void setBlankNumber(Long blankNumber) {
        this.blankNumber = blankNumber;
    }

    public Integer getAuditorsCoupons() {
        return auditorsCoupons;
    }

    public void setAuditorsCoupons(Integer auditorsCoupons) {
        this.auditorsCoupons = auditorsCoupons;
    }

    public Integer getFlightCoupon() {
        return flightCoupon;
    }

    public void setFlightCoupon(Integer flightCoupon) {
        this.flightCoupon = flightCoupon;
    }

    public Integer getTravelAdvisorID() {
        return travelAdvisorID;
    }

    public void setTravelAdvisorID(Integer travelAdvisorID) {
        this.travelAdvisorID = travelAdvisorID;
    }

    public Integer getSysadminID() {
        return sysadminID;
    }

    public void setSysadminID(Integer sysadminID) {
        this.sysadminID = sysadminID;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    private Integer blankID, blankType, auditorsCoupons, flightCoupon, travelAdvisorID, sysadminID;
    private Long blankNumber;
    private String airlineName;
}
