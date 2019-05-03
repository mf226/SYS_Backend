package facade;

import entity.Car;
import java.util.ArrayList;
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

}
