package utils;

import entity.Role;
import entity.User;
import java.util.Date;
import javax.persistence.EntityManager;

public class SetupTestUsers {

    public static void main(String[] args) {

        createTestUsers();

    }

    public static void createTestUsers() {
        EntityManager em = PuSelector.getEntityManagerFactory("pu").createEntityManager();
//
//    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
//    // CHANGE the three passwords below, before you uncomment and execute the code below
//    
//    //throw new UnsupportedOperationException("REMOVE THIS LINE, WHEN YOU HAVE READ WARNING");
//
        em.getTransaction().begin();
        Role userRole = new Role("user");
        Role adminRole = new Role("admin");
        User user = new User("TestUser", "TestUser", "Test", "Testersen", "test@test.test", new Date(), "male", "12345678", "Alive", "1234567890");
        user.addRole(userRole);
        User admin = new User("TestAdmin", "TestAdmin", "Test2", "Testersen2", "test2@test2.test", new Date(), "male", "87654321", "Alive", "0987654321");
        admin.addRole(adminRole);
        User jesper = new User("Jepper_KickFlip", "123", "Jesper", "C", "jesper@jepper.dk", new Date(), "male", "33333333", "Active", "1234567891");
        jesper.addRole(userRole);
        User mads = new User("fenonline", "123", "Mads", "F", "mf226@fenonline.dk", new Date(), "male", "44444444", "Active", "1234567892");
        mads.addRole(userRole);
        User michael = new User("Duen420", "123", "Michael", "P", "mich2131231231231243562435223d@duen.test", new Date(), "male", "55555555", "Active", "1234567893");
        michael.addRole(userRole);
        User christian = new User("Ryge", "123", "Christian", "R", "ryge@test.test", new Date(), "male", "66666666", "Banned", "1234567894");
        christian.addRole(userRole);
        em.persist(userRole);
        em.persist(adminRole);
        em.persist(user);
        em.persist(admin);
        em.persist(jesper);
        em.persist(mads);
        em.persist(michael);
        em.persist(christian);
        em.getTransaction().commit();
        System.out.println("PW: " + user.getUserPass());
        System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
        System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
        System.out.println("Created TEST Users");
    }

}
