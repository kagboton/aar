package fr.orleans.miage.aar.tp7.backend.services;

import fr.orleans.miage.aar.tp7.backend.modele.Client;
import fr.orleans.miage.aar.tp7.backend.modele.Compte;
import fr.orleans.miage.aar.tp7.backend.modele.Livret;

public interface BanqueService {

    /**
     * Créer un nouveau client
     * @param client
     */
    void creerClient(Client client);

    /**
     * Créer un compte pour un client
     * @param compte
     */
    void creerCompte(Compte compte);

    /**
     * Supprimer un client par son id
     * @param id
     */
    void deleteClientById(long id);

    /**
     * Effectuer un virement d'un compte vers un autre
     * @param a
     * @param b
     * @param c
     */
    void virement(long a, long b, Double c);

    /**
     * Recupérer un client par son id
     * @param id
     * @return
     */
    Client getClientById(long id);

    /**
     * Recupérer un client par son username
     * @param username
     * @return
     */
    Client getClientByUSername(String username);

    /**
     * Récupérer tous les comptes d'un client par son id
     * @param id
     * @return
     */
    Iterable<Compte> getCompteOfClient(long id);

    /**
     * Tous les clients
     * @return
     */
    Iterable<Client> getAllClients();

    /**
     * Tous les comptes bancaires
     * @return
     */
    Iterable<Compte> getAllComptes();

    /**
     * Tous les livrets
     * @return
     */
    Iterable<Livret> getAllLivrets();
}
