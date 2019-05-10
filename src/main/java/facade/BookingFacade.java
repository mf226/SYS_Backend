package facade;

import dto.BookinginformationDTO;
import entity.Bookinginformation;
import java.util.ArrayList;
import java.util.List;
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

    public List<Bookinginformation> getAllBookings() {
        EntityManager em = emf.createEntityManager();
        List<Bookinginformation> bookings;
        try {
            bookings = em.createNamedQuery("Car.findAll", Bookinginformation.class).getResultList();
        } finally {
            em.close();
        }
        return bookings;
    }
    
    public BookinginformationDTO createBooking(Bookinginformation booking) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(booking);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BookinginformationDTO(booking);
    }
    
}
