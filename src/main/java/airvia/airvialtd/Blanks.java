package airvia.airvialtd;

public class Blanks {

    public Blanks(Integer blankID, Integer blankType, Integer blankNumber, String airlineName, Integer auditorsCoupons, Integer flightCoupon, Integer travelAdvisorID, Integer sysadminID) {
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

    public Integer getBlankNumber() {
        return blankNumber;
    }

    public void setBlankNumber(Integer blankNumber) {
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

    private Integer blankID, blankType, blankNumber, auditorsCoupons, flightCoupon, travelAdvisorID, sysadminID;
    private String airlineName;
}
