package dto;
//test
import entity.BookingInformation;

public class BookinginformationDTO {

    private int id;
    private String startPeriod;
    private String endPeriod;
    private String created;
    private double price;
    private CarDTO car;
    private String userName;

    public BookinginformationDTO(String startPeriod, String endPeriod, String created, double price, CarDTO car, String userName) {
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.created = created;
        this.price = price;
        this.car = car;
        this.userName = userName;
    }

    public BookinginformationDTO(BookingInformation booking) {
        this.id = booking.getId();
        this.startPeriod = booking.getStartPeriod().toString();
        this.endPeriod = booking.getEndPeriod().toString();
        this.created = booking.getCreated().toString();
        this.price = booking.getPrice();
        this.car = new CarDTO(booking);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(String startPeriod) {
        this.startPeriod = startPeriod;
    }

    public String getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(String endPeriod) {
        this.endPeriod = endPeriod;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
