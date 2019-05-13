package facade;

import entity.Country;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Fen
 */
public class CountryFacade {

    private static EntityManagerFactory emf;
    private static CountryFacade instance;

    private CountryFacade() {
    }

    public static CountryFacade getInstance(EntityManagerFactory factory) {
        if (instance == null) {
            emf = factory;
            instance = new CountryFacade();
        }
        return instance;
    }

    public Country getCountryByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Country.findByCountry", Country.class).setParameter("country", name).getSingleResult();
        } finally {
            em.close();
        }
    }

}
