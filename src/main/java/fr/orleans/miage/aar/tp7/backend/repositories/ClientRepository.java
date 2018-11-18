package fr.orleans.miage.aar.tp7.backend.repositories;

import fr.orleans.miage.aar.tp7.backend.modele.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {

    List<Client> findByUsername(String username);

}
