package dto;

import java.util.Collection;

public class CarDTO {

    private String regno;
    private double price;
    private String manufactor;
    private String model;
    private String type;
    private int releaseYear;
    private int drivingDist;
    private int seats;
    private String drive;
    private String fuelType;
    private String longitude;
    private String latitude;
    private String address;
    private String country;
    private String company;
    //private Collection<BookingInformationDTO> bookingInformationCollection;

    public CarDTO(String regno, double price, String manufactor, String model, String type, int releaseYear, int drivingDist, int seats, String drive, String fuelType, String longitude, String latitude, String address, String country, String company) {
        this.regno = regno;
        this.price = price;
        this.manufactor = manufactor;
        this.model = model;
        this.type = type;
        this.releaseYear = releaseYear;
        this.drivingDist = drivingDist;
        this.seats = seats;
        this.drive = drive;
        this.fuelType = fuelType;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.country = country;
        this.company = company;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getManufactor() {
        return manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getDrivingDist() {
        return drivingDist;
    }

    public void setDrivingDist(int drivingDist) {
        this.drivingDist = drivingDist;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

//    public Collection<BookingInformationDTO> getBookingInformationCollection() {
//        return bookingInformationCollection;
//    }
//
//    public void setBookingInformationCollection(Collection<BookingInformationDTO> bookingInformationCollection) {
//        this.bookingInformationCollection = bookingInformationCollection;
//    }
}
