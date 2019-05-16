package facade;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import dto.BookinginformationDTO;
import entity.BookingInformation;
import entity.Car;
import exceptions.APIErrorException;
import exceptions.BookingException;
import exceptions.FacadeException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Fen
 */
public class BookingFacade {

    private static EntityManagerFactory emf;
    private static BookingFacade instance;

    public static BookingFacade getInstance(EntityManagerFactory factory) {
        if (instance == null) {
            emf = factory;
            instance = new BookingFacade();
        }
        return instance;
    }

    public List<BookingInformation> getAllBookings() {
        EntityManager em = emf.createEntityManager();
        List<BookingInformation> bookings;
        try {
            bookings = em.createNamedQuery("Car.findAll", BookingInformation.class).getResultList();
        } finally {
            em.close();
        }
        return bookings;
    }

    public BookinginformationDTO createBooking(BookingInformation booking) throws FacadeException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(booking);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new FacadeException("Something went wrong while creating your order!");
        } finally {
            em.close();
        }
        return new BookinginformationDTO(booking);
    }

    public List<BookinginformationDTO> getAllBookingsByUserName(String userName) {
        EntityManager em = emf.createEntityManager();
        List<BookinginformationDTO> DTO = new ArrayList();
        try {
            List<BookingInformation> bookings = em.createNamedQuery("BookingInformation.findAllByUser", BookingInformation.class).setParameter("userName", userName).getResultList();
            for (BookingInformation booking : bookings) {
                DTO.add(new BookinginformationDTO(booking));
            }
        } finally {
            em.close();
        }
        return DTO;
    }

    public BookinginformationDTO createBooking(String company, String regNo, String start, String end) throws BookingException {
        EntityManager em = emf.createEntityManager();
        BookinginformationDTO bookingInformationDTO;
        try {
            em.getTransaction().begin();
            Car car = em.find(Car.class, regNo);
            if (car == null) {
                throw new BookingException("There is no car with the regNo: " + regNo);
            }
            Date s = StringToDate(start);
            Date e = StringToDate(end);
            // Re check availability
            List<BookingInformation> bookings = em.createNamedQuery("BookingInformation.findAll", BookingInformation.class).getResultList();
            for (BookingInformation booking : bookings) {
                if (booking.getCarRegNo().equals(regNo)) {
                    Date bookingStartDate = booking.getStartPeriod();
                    Date bookingEndDate = booking.getEndPeriod();
                    boolean inside = s.after(bookingStartDate) && e.before(bookingEndDate);
                    boolean through = s.before(bookingStartDate) && e.after(bookingEndDate);
                    boolean atStart = s.before(bookingStartDate) && e.after(bookingStartDate);
                    boolean atEnd = s.before(bookingEndDate) && e.after(bookingEndDate);
                    if (inside || through || atStart || atEnd) {
                        throw new BookingException("This car (" + car.getRegno() + ") and these two dates (" + s + " to " + e + ") is already booked!");
                    }
                }
            }
            double price = car.getPrice() * getDays(s, e);
            s.setHours(10);
            e.setHours(8);
            //Date startPeriod, Date endPeriod, Date created, double price, String company, String carRegNo, String manufactor, String model, String type, int releaseYear, int drivingDist, int seats, String drive, String fuelType, String longitude, String latitude, String address) {
            BookingInformation bi = new BookingInformation(s, e, Calendar.getInstance(TimeZone.getTimeZone("da_DK")).getTime(), price, company, car.getRegno(), car.getManufactor(), car.getModel(), car.getType(), car.getReleaseYear(), car.getDrivingDist(), car.getSeats(), car.getDrive(), car.getFuelType(), car.getLongitude(), car.getLatitude(), car.getAddress());
            //bi.setCar(car);
            em.persist(bi);
            em.getTransaction().commit();
            bookingInformationDTO = new BookinginformationDTO(bi);
        } catch (ParseException e) {
            throw new BookingException("One or Both of the dates are invalid!");

        } finally {
            em.close();
        }
        return bookingInformationDTO;
    }

    private Date StringToDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObj = sdf.parse(date);
        return dateObj;
    }

    private long getDays(Date s, Date e) throws BookingException {
        if (e.before(s)) {
            throw new BookingException("Start date:" + s + " and end date: " + e + " is not a valid period");
        }
        Date d1 = new Date(s.getYear(), s.getMonth(), s.getDay(), 0, 0, 0);
        Date d2 = new Date(e.getYear(), e.getMonth(), e.getDay(), 0, 0, 0);
        long t1 = d1.getTime();
        long t2 = d2.getTime();
        if (t2 < t1) {
            throw new BookingException("While calculating days between dates an error occurred! t2:" + t2 + " - t1:" + t1 + " = " + (t2 - t1));
        }
        System.out.println("t2:" + t2);
        System.out.println("t1:" + t1);
        System.out.println("t2-t1:" + (t2 - t1));
        long seconds = (t2 - t1) / 1000;
        System.out.println("seconds:" + seconds);
        long minutes = seconds / 60;
        System.out.println("minutes:" + minutes);
        long hours = minutes / 60;
        System.out.println("hours:" + hours);
        long days = hours / 24;
        System.out.println("days:" + days);
        if (days < 0) {
            throw new BookingException("While calculating days between dates an error occurred! Days is lower than 0 (days: " + days + ")");
        }
        return days;

    }

    public boolean cancelOrderByOrderId(String orderId) throws APIErrorException, BookingException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            BookingInformation booking = em.find(BookingInformation.class, orderId);
            String company = booking.getCompany().toLowerCase();
            int companyOrderId = booking.getCompanyId();
            switch (company) {
                case "dueinator":
                    String url = "https://dueinator.dk/jwtbackend/api/car/cancel/" + companyOrderId;
                    fetch(url, company);
                    break;
                case "deck-cs":
                    break; // Not implemented yet!
                case "ttt":
                    break; // Nothing shall happen!
                default:
                    throw new BookingException("Something went wrong while trying to cancel your booking!");
            }
            em.remove(booking);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return true;
    }

    private boolean fetch(String url, String company) throws APIErrorException {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json;charset=UTF-8");
            con.setRequestProperty("User-Agent", "server");
            if (con.getResponseCode() != 200) {
                throw new APIErrorException(con.getResponseMessage());
            }
            JsonReader reader = new JsonReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            boolean b = false;
            try {
                if (reader.hasNext()) {
                    reader.beginObject();
                    b = reader.nextBoolean();
                    reader.endObject();
                }
            } catch (IOException ex) {
                throw new APIErrorException("The booking did not cancel in the target API");
            } finally {
                reader.close();
            }
            return b;
        } catch (IOException ex) {
            throw new APIErrorException(ex.getMessage());
        }
    }
}
