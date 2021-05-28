package model;

public class detail {
    private String centreName;
    private String address;
    private String slotTime;
    private String vaccineName;

    private String feeType;
    private String ageLimit;
    private String availability;
    private String latitude;
    private String longitude;
    public detail(){

    }

    public detail(String centreName, String address, String slotTime, String vaccineName, String feeType, String ageLimit, String availability, String latitude,String longitude) {
        this.centreName = centreName;
        this.address = address;
        this.slotTime = slotTime;
        this.vaccineName = vaccineName;
        this.feeType = feeType;
        this.ageLimit = ageLimit;
        this.availability = availability;
        this.latitude = latitude;
        this.longitude=longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }





    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSlotTime() {
        return slotTime;
    }

    public void setSlotTime(String slotTime) {
        this.slotTime = slotTime;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

}
