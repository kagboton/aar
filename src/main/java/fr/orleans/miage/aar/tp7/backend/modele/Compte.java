package fr.orleans.miage.aar.tp7.backend.modele;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Client titulaire;

    private double solde;

    @Temporal(TemporalType.DATE)
    private Date dateOuverture;

    /**
     * Constructeur avec les paramètres
     * Le constructeur par défaut --> utilisé pour JPA est généré par lombik
     * @param titulaire
     * @param solde
     * @param dateOuverture
     */
    public Compte(Client titulaire, double solde, Date dateOuverture) {
        this.titulaire = titulaire;
        this.solde = solde;
        this.dateOuverture = dateOuverture;
    }

}
