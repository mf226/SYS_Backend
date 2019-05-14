package testutils;

import entity.BookingInformation;
import entity.Car;
import entity.Country;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class TestUtils {

    // Test data
    private static final String[] R = {"AA12345", "BB12345", "CC12345", "DD12345", "EE12345"};
    private static final double[] P = {50.0, 75.0, 100.0, 125.0, 67.5};
    private static final String[] MA = {"Fiat", "Toyota", "Opel", "Audi", "Volkswagen"};
    private static final String[] MO = {"Punto", "Yaris", "Corsa D", "A3", "Up!"};
    private static final int[] Y = {2016, 2015, 2014, 2013, 2012};
    private static final int[] D = {25433, 54326, 62144, 85432, 100543};

    public static void setupTestData(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            //Delete existing cars to get a "fresh" database
            em.createQuery("delete from Rating").executeUpdate();
            em.createQuery("delete from BookingInformation").executeUpdate();
            em.createQuery("delete from Car").executeUpdate();
            em.createQuery("delete from Country").executeUpdate();
            em.createQuery("delete from User").executeUpdate();
            em.createQuery("delete from Role").executeUpdate();
            //Create country
            Country country = new Country(1, "Denmark");
            em.persist(country);
            //Create new cars
            for (int i = 0; i < 5; i++) {
                Car car = new Car(R[i], D[i], P[i], MA[i], MO[i], Y[i], "Hatchback", "Manual", "Gas", 5, "12.6509822", "55.6091282", "Copenhagen Airport");
                car.setCountry(country);
                em.persist(car);
            }
            //Get all cars to attach bookings
            List<Car> cars = em.createNamedQuery("Car.findAll", Car.class).getResultList();
            Collections.shuffle(cars); 
            //Create bookings
            for (int i = 0; i < 2; i++) {
                Car car = cars.get(i);
                BookingInformation booking = new BookingInformation(STD("2019-05-05"), STD("2019-05-13"), new Date(), car.getPrice()*8, "Test", car.getRegno(), car.getManufactor(), car.getModel(), car.getType(), car.getReleaseYear(), car.getDrivingDist(), car.getSeats(), car.getDrive(), car.getFuelType(), car.getLongitude(), car.getLatitude(), car.getAddress());
            }
            System.out.println("Saved test data to database");
            em.getTransaction().commit();
        } catch (ParseException ex) {
            System.out.println("------------------------------------------------");
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println("ERROR-ERROR-ERROR-ERROR--ERROR-ERROR-ERROR-ERROR");
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println("------------------------------------------------");
        } finally {
            em.close();
        }
    }

    private static Date STD(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObj = sdf.parse(date);
        return dateObj;
    }

}
