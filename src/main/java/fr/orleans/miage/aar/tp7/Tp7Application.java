package fr.orleans.miage.aar.tp7;

import fr.orleans.miage.aar.tp7.backend.modele.Client;
import fr.orleans.miage.aar.tp7.backend.modele.Compte;
import fr.orleans.miage.aar.tp7.backend.modele.Livret;
import fr.orleans.miage.aar.tp7.backend.repositories.ClientRepository;
import fr.orleans.miage.aar.tp7.backend.services.BanqueService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;

@SpringBootApplication
public class Tp7Application implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(Tp7Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Tp7Application.class, args);
    }

    /**
     * View controller get simple pages
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login");
        registry.addViewController("/").setViewName("index");
    }


    @Bean
    public CommandLineRunner demo(BanqueService banqueService, PasswordEncoder passwordEncoder){
        return (args) ->{

            //Creation de quelques Clients
            Client client1 = new Client("jbauer",passwordEncoder.encode("test"), "Bauer", "Jack", "Washington");
            Client client2 = new Client("cobrian", passwordEncoder.encode("test"),"O'Brian", "Chloe","Chicago");
            Client client3 = new Client("dpalmer",passwordEncoder.encode("test"),"Palmer", "David", "Denvers");
            Client admin = new Client("admin",passwordEncoder.encode("admin"),"admin", "admin", "LA");
            admin.addRole("ROLE_ADMIN");

            banqueService.creerClient(client1);
            banqueService.creerClient(client2);
            banqueService.creerClient(client3);
            banqueService.creerClient(admin);


            //Creation de compte et livrets et persistence en bdd
            banqueService.creerCompte(new Compte(client1, 1538, new SimpleDateFormat("dd/MM/yy").parse(
                    "31/01/2017")));
            banqueService.creerCompte(new Compte(client2, 802, new SimpleDateFormat("dd/MM/yy").parse(
                    "10/08/2018")));
            banqueService.creerCompte(new Livret(client2, 150,new SimpleDateFormat("dd/MM/yy").parse(
                    "25/03/2018"), 1.8 ));
            banqueService.creerCompte(new Compte(client3, -100, new SimpleDateFormat("dd/MM/yy").parse(
                    "14/11/2018")));
            banqueService.creerCompte(new Compte(admin, 1000065400, new SimpleDateFormat("dd/MM/yy").parse(
                    "01/12/2018")));



            //Récupération des clients
            log.info("Clients retrouvés avec findAll():");
            log.info("-------------------------------");
            for (Client client: banqueService.getAllClients()){
                log.info(client.toString());
            }
            log.info("");

            //Récupération des comptes et livrets
            log.info("Comptes retrouvés avec findAll():");
            log.info("-------------------------------");
            for (Compte compte: banqueService.getAllComptes()){
                log.info(compte.toString());
            }
            log.info("");


        };
    }


}
