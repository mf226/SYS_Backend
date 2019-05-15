package unittest;

import dto.CarDTO;
import exceptions.APIErrorException;
import facade.DataFetcher;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.PuSelector;

public class FetcherTest {

    private static DataFetcher fetcher;

    @BeforeClass
    public static void setUpClass() {
        fetcher = DataFetcher.getInstance(PuSelector.getEntityManagerFactory("pu_unit_test_mock"));
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void getAllCars() throws APIErrorException{
        List<CarDTO> cars = fetcher.getAllCarsAllAPIs();
        assertNotNull(cars);
    }

    @Test
    public void getAllAvailableCars() throws APIErrorException {
        String start = "2019-05-03";
        String end = "2019-05-13";
        List<CarDTO> cars = fetcher.getAllAvailableCarsAllAPIs(start, end);
        assertNotNull(cars);
    }
    
    @Test(expected = APIErrorException.class)
    public void getAllAvailableCars2() throws Exception {
        String start = "20190503";
        String end = "2019-05-13";
        List<CarDTO> cars = fetcher.getAllAvailableCarsAllAPIs(start, end);
    }
    
    @Test
    public void getSpecificCar() throws Exception {
        String regno = "AH07908";
        String company = "dueinator";

        CarDTO car = fetcher.getSpecificCar(regno, company);
        assertEquals(regno, car.getRegno());
    }
    
    @Test(expected = APIErrorException.class)
    public void getSpecificCar2() throws Exception {
        String regno = "AH07908";
        String company = "WrongCompanyName";

        CarDTO car = fetcher.getSpecificCar(regno, company);
    }
    
    @Test(expected = APIErrorException.class)
    public void getSpecificCar3() throws Exception {
        String regno = "ZZZZZZZ";
        String company = "dueinator";

        CarDTO car = fetcher.getSpecificCar(regno, company);
    }

}
