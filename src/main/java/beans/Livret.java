package beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Livret extends Compte {
    @Id
    private double tauxInteret;

    public Livret(long id, Client titulaire, double solde, Date dateOuverture, double tauxInteret) {
        super(id, titulaire, solde, dateOuverture);
        this.tauxInteret = tauxInteret;
    }

    public Livret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    public Livret() {
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }
}
