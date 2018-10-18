package persistence.run;

import persistence.beans.Client;
import persistence.beans.Compte;
import persistence.beans.Livret;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import persistence.service.BanqueService;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SpringRun {

    // couche persistence.service : SPRING
    private static BanqueService service;

    public static void main(String[] args) throws ParseException {

        //Configuration de l'application
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");

        // récupération de la couche persistence.service
        service = (BanqueService)ctx.getBean("banqueService");

        // on vide la base
       clean();

       //on la rempli
        fill();

        // on vérifie visuellement
        dumpClients();
        dumpComptes();
        dumpLivrets();
        dumpClientsComptes();
        virement();
        dumpComptes();


    }

    //virement
    private static void virement() {
        service.virement(2014, 564, 500.00);
    }

    // affichage contenu table Client
    private static void dumpClients() {
        System.out.format("[Clients]%n");
        for (Client c : service.getAllClients()) {
            System.out.print(c);
            Client c2 = service.getClient(c.getId());
            System.out.println("|"+c2);
        }
    }

    // affichage contenu table Livret
    private static void dumpLivrets() {
        System.out.format("[Livrets]%n");
        for (Livret a : service.getAllLivrets()) {
            System.out.println(a);
        }
    }
    // affichage des comptes
    private static void dumpComptes() {
        System.out.format("[Compte]%n");
        for (Compte a : service.getAllComptes()) {
            System.out.println(a);
        }
    }

    // affichage des clients, avec leurs comptes respectifs
    private static void dumpClientsComptes() {
        System.out.println("[Clients/comptes]");
        for (Client p : service.getAllClients()) {
            for (Compte a : service.getComptesOfClient(p.getId())) {
                System.out.format("[%s,%s]%n", p.getNom(), a.getId());
            }
        }
    }


    //remplissage de la base
    public static void fill() throws ParseException{
        //Création de clients
        Client c1 = new Client(100, "Martin", "Paul", "Orléans");
        Client c2 = new Client(101, "Kaniel", "Outis", "Panama");

        // Ajout des Comptes/Livrets
        c1.addCompte(new Compte(2014, c1,2300.0,new SimpleDateFormat("dd/MM/yy").parse(
                "31/01/2010")));
        c2.addCompte(new Compte(564, c2,655.0,new SimpleDateFormat("dd/MM/yy").parse(
                "31/01/2010")));

        c2.addCompte(new Livret(2478, c2,655.0,new SimpleDateFormat("dd/MM/yy").parse(
                "31/01/2010"), 0.05));

        // persistance des Clients avec leurs comptes/livrets
        service.saveClients(new Client[]{c1, c2});
    }

    //Suppression de tous les clients
    public static void clean(){
        for (Client client:service.getAllClients()){
            service.deleteClient(client.getId());
        }
    }
}
