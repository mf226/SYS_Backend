package facade;

import dto.CarDTO;
import entity.BookingInformation;
import entity.Car;
import entity.Country;
import entity.User;
import exceptions.FacadeException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CarFacade {

    private static EntityManagerFactory emf;
    private static CarFacade instance;

    public static CarFacade getInstance(EntityManagerFactory factory) {
        if (instance == null) {
            emf = factory;
            instance = new CarFacade();
        }
        return instance;
    }

    public List<CarDTO> getAllCars() {
        EntityManager em = emf.createEntityManager();
        List<CarDTO> cars;
        try {
            cars = em.createNamedQuery("CarDTO.findAll", CarDTO.class).getResultList();
        } finally {
            em.close();
        }
        return cars;
    }

    List<CarDTO> getAllAvailableCars(String start, String end) throws FacadeException {
        EntityManager em = emf.createEntityManager();
        List<CarDTO> carsDTO = new ArrayList();
        try {
            List<Car> cars = em.createNamedQuery("Car.findAll", Car.class).getResultList();
            List<BookingInformation> bookings = em.createNamedQuery("BookingInformation.findAll", BookingInformation.class).getResultList();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(start);
            Date endDate = sdf.parse(end);
            for (BookingInformation booking : bookings) {
                Date s = booking.getStartPeriod();
                Date e = booking.getEndPeriod();
                boolean inside = startDate.after(s) && endDate.before(e);
                boolean through = startDate.before(s) && endDate.after(e);
                boolean atStart = startDate.before(s) && endDate.after(s);
                boolean atEnd = startDate.before(e) && endDate.after(e);
                if (inside || through || atStart || atEnd) {
                    cars.removeIf(car -> (car.getRegno().equals(booking.getCarRegNo())));
                }
            }
            for (Car car : cars) {
                CarDTO dto = new CarDTO(car);
                carsDTO.add(dto);
            }
        } catch (ParseException ex) {
            throw new FacadeException("Date format is unsupported!");
        } finally {
            em.close();
        }
        return carsDTO;
    }

    public CarDTO listOwnCar(CarDTO dto) {
        EntityManager em = emf.createEntityManager();
        try {
            Country country = CountryFacade.getInstance(emf).getCountryByName(dto.getCountry());
            User user = UserFacade.getInstance(emf).getUserByUserName(dto.getUserName());
            Car car = new Car(dto, country, user);
            em.getTransaction().begin();
            em.persist(car);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return dto;
    }

    CarDTO getSpecificCar(String regno) throws FacadeException {
        EntityManager em = emf.createEntityManager();
        CarDTO car;
        try {
            car = em.createNamedQuery("CarDTO.findByRegno", CarDTO.class).setParameter("regno", regno).getSingleResult();
        } catch (Exception ex) {
            throw new FacadeException(ex.getMessage());
        } finally {
            em.close();
        }

        return car;

    }

    public Object getAllOwnCarsByUserName(String userName) {
        EntityManager em = emf.createEntityManager();
        List<CarDTO> DTO = new ArrayList();
        try {
            List<Car> cars = em.createNamedQuery("Car.findAllByUser", Car.class).setParameter("userName", userName).getResultList();
            for (Car car : cars) {
                DTO.add(new CarDTO(car));
            }
        } finally {
            em.close();
        }
        return DTO;
    }

}
