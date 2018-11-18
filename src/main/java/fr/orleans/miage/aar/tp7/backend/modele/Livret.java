package fr.orleans.miage.aar.tp7.backend.modele;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@Data
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
@Entity
public class Livret extends Compte {

    private double tauxInteret;

    /**
     * Constructeur avec des paramètres
     * Constructeur par défaut --> utilisé pour JPA gégéré par lombok
     * @param titulaire
     * @param solde
     * @param dateOuverture
     * @param tauxInteret
     */
    public Livret(Client titulaire, double solde, Date dateOuverture, double tauxInteret) {
        super(titulaire, solde, dateOuverture);
        this.tauxInteret = tauxInteret;
    }

    public Livret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }


}
