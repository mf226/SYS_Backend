package facade;

import dto.CarDTO;
import entity.Car;
import entity.Country;
import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Fen
 */
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

    public List<Car> getAllCars() {
        EntityManager em = emf.createEntityManager();
        List<Car> cars;
        try {
            cars = em.createNamedQuery("Car.findAll", Car.class).getResultList();
        } finally {
            em.close();
        }
        return cars;
    }

    public CarDTO listOwnCar(CarDTO dto) {
        EntityManager em = emf.createEntityManager();
        try {
            Country country = CountryFacade.getInstance(emf).getCountryByName(dto.getCountry());
            User user = UserFacade.getInstance(emf).getUserById(dto.getUserName());
            Car car = new Car(dto, country, user);
            em.getTransaction().begin();
            em.persist(car);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return dto;
    }

}
