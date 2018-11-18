package fr.orleans.miage.aar.tp7.backend.repositories;

import fr.orleans.miage.aar.tp7.backend.modele.Compte;
import org.springframework.data.repository.CrudRepository;

public interface CompteRepository extends CrudRepository<Compte, Long> {
}
