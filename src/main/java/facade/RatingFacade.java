package facade;

import dto.RatingDTO;
import entity.Rating;
import entity.User;
import exceptions.FacadeException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Fen
 */
public class RatingFacade {

    private static EntityManagerFactory emf;
    private static RatingFacade instance;

    private RatingFacade() {
    }

    public static RatingFacade getInstance(EntityManagerFactory factory) {
        if (instance == null) {
            emf = factory;
            instance = new RatingFacade();
        }
        return instance;
    }

    public RatingDTO getRatingByUser(String userName) throws FacadeException {
        EntityManager em = emf.createEntityManager();
        List<Rating> ratings;
        try {
            ratings = em.createNamedQuery("Rating.findByUser", Rating.class).setParameter("userName", userName).getResultList();
        } finally {
            em.close();
        }
        if (ratings == null || ratings.isEmpty()) {
            throw new FacadeException("That user has no ratings");
        }
        int sumOfRatings = 0;
        for (Rating r : ratings) {
            sumOfRatings += r.getRating();
        }
//        DecimalFormat df = new DecimalFormat("#.##");
//        df.setRoundingMode(RoundingMode.CEILING);
//        double average = df.format

        return new RatingDTO(userName, ((double) sumOfRatings / ratings.size()));
    }

    public RatingDTO createRating(RatingDTO dto) throws FacadeException {
        EntityManager em = emf.createEntityManager();
        if (dto.getRating() < 1 || dto.getRating() > 5) {
            throw new FacadeException("A rating must be between 1 and 5 stars");
        }
        try {
            User user = UserFacade.getInstance(emf).getUserByUserName(dto.getUserName());
            Rating rating = new Rating((int) dto.getRating(), user);
            em.getTransaction().begin();
            em.persist(rating);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return dto;
    }
}
