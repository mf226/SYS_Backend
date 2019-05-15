package facade;

import dto.BookinginformationDTO;
import entity.BookingInformation;
import exceptions.FacadeException;
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

    public Object getAllBookingsByUserName(String userName) {
        EntityManager em = emf.createEntityManager();
        List<BookinginformationDTO> DTO = new ArrayList();
        try {
            List<BookingInformation> bookings = em.createNamedQuery("BookingInformation.findAllByUser", BookingInformation.class).getResultList();
            for (BookingInformation booking : bookings) {
                DTO.add(new BookinginformationDTO(booking));
            }
        } finally {
            em.close();
        }
        return DTO;
    }

}
