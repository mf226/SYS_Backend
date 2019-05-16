package facade;

import dto.RatingDTO;
import entity.Rating;
import exceptions.FacadeException;
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

        return new RatingDTO(userName, (sumOfRatings / ratings.size()));
    }

    public RatingDTO createRating(RatingDTO fromJson) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
