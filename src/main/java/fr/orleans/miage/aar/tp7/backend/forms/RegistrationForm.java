package fr.orleans.miage.aar.tp7.backend.forms;

import fr.orleans.miage.aar.tp7.backend.modele.Client;
import fr.orleans.miage.aar.tp7.backend.modele.Compte;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotEmpty;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
public class RegistrationForm {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String nom;
    @NotEmpty
    private String prenom;
    @NotEmpty
    private String adresse;

    public Client toUser(PasswordEncoder passwordEncoder) {
        Client client = new Client(username, passwordEncoder.encode(password), nom, prenom, adresse);
        try {
            client.addCompte(new Compte(client, 0.0, new SimpleDateFormat("dd/MM/yy").parse(
                    "25/03/2018")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  client;
    }


}
