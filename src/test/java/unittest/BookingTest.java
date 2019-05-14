package unittest;

import dto.BookinginformationDTO;
import entity.BookingInformation;
import entity.Car;
import exceptions.FacadeException;
import facade.BookingFacade;
import facade.CarFacade;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import testutils.TestUtils;
import utils.PuSelector;

/**
 *
 * @author mich5
 */
public class BookingTest {

//    private static BookingFacade facade;
//
//    @BeforeClass
//    public static void setUpClass() {
//        EntityManagerFactory emf = PuSelector.getEntityManagerFactory("pu_unit_test_mock");
//        facade = BookingFacade.getInstance(emf);
//        TestUtils.setupTestData(emf);
//    }
//
//    @Test
//    public void testGetAllBookings() {
//        List<BookingInformation> bookings = facade.getAllBookings();
//        assertEquals(2, bookings.size());
//    }
//
//    @Test
//    public void testCreateBooking() throws FacadeException {
//        Car car = new CarFacade().getAllCars().get(4);
//        BookingInformation booking = new BookingInformation(new Date("2019-05-05"), new Date("2019-05-13"), new Date(), 999.99, "Test", car.getRegno(), car.getManufactor(), car.getModel(), car.getType(), car.getReleaseYear(), car.getDrivingDist(), car.getSeats(), car.getDrive(), car.getFuelType(), car.getLongitude(), car.getLatitude(), car.getAddress());
//        BookinginformationDTO bookingDTO = facade.createBooking(booking);
//        assertNotNull(bookingDTO);
//    }

}
