//package unittest;
//
//import dto.CarDTO;
//import exceptions.APIErrorException;
//import exceptions.AuthenticationException;
//import facade.DataFetcher;
//import java.util.List;
//import javax.persistence.EntityManagerFactory;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import testutils.TestUtils;
//import utils.PuSelector;
//
//public class CarTest {
//
//    private static DataFetcher fetcher;
//
//    @BeforeClass
//    public static void setUpClass() {
//        fetcher = new DataFetcher();
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Test
//    public void getAllCars() throws Exception {
//        List<CarDTO> cars = fetcher.getAllCarsAllAPIs();
//        assertNotNull(cars);
//    }
//
//    @Test
//    public void getAllAvailableCars() throws Exception {
//        String start = "2019-05-03";
//        String end = "2019-05-13";
//        List<CarDTO> cars = fetcher.getAllAvailableCarsAllAPIs(start, end);
//        assertNotNull(cars);
//    }
//    
//    @Test(expected = APIErrorException.class)
//    public void getAllAvailableCars2() throws Exception {
//        String start = "20190503";
//        String end = "2019-05-13";
//        List<CarDTO> cars = fetcher.getAllAvailableCarsAllAPIs(start, end);
//    }
//
//}
