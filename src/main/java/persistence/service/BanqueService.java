package persistence.service;

import persistence.beans.Client;
import persistence.beans.Compte;
import persistence.beans.Livret;

import java.util.Collection;

public interface BanqueService {

    public void createClient(Client client);
    public Client getClient(long id);
    public Collection<Client> getAllClients();
    public Collection<Compte> getAllComptes();
    public Collection<Livret> getAllLivrets();
    public void saveClients(Client... clients);
    public void deleteClient(long id);

    public void virement(long noCompteSource, long noCompteDestanation, Double montant);

    /**
     *
     * @param id
     * @return tous les comptes d'un client par son id
     */
    public Collection<Compte> getComptesOfClient(long id);
}
