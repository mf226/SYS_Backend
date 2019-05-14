package facade;

import dto.BookinginformationDTO;
import entity.BookingInformation;
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
    
    public BookinginformationDTO createBooking(BookingInformation booking) {
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
