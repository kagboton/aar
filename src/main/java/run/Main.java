package run;

import beans.Client;
import beans.Compte;
import beans.Livret;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Client c1 = new Client(1, "Kilian", "Agboton", "Orléans");
        Client c2 = new Client(2, "Chloé", "O'Brian", "Washington DC");
        Client c3 = new Client(3, "Jack", "Bauer", "NYC");

        Compte cpt1 = new Compte(1, c1, 58200, new Date());
        Compte cpt2 = new Compte(2, c2, 6541684, new Date());
        Compte cpt3 = new Compte(3, c3, 5787, new Date());
        Compte cpt4 = new Livret(4, c1, 891798, new Date(), 2.0);



        c1.addCompte(cpt1);
        c1.addCompte(cpt4);
        c2.addCompte(cpt2);
        c3.addCompte(cpt3);

        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("banquePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.persist(c1);
        em.persist(c2);
        em.persist(c3);


        tx.commit();
        em.close();
        emf.close();

    }
}
