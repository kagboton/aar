package persistence.service;

import persistence.beans.Client;
import persistence.beans.Compte;
import persistence.beans.Livret;
import persistence.dao.ClientDao;
import persistence.dao.CompteDao;
import persistence.dao.LivretDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
public class BanqueServiceImpl implements BanqueService {

    private final ClientDao clientDao;
    private final CompteDao compteDao;
    private final LivretDao livretDao;

    public BanqueServiceImpl(ClientDao clientDao, CompteDao compteDao, LivretDao livretDao) {
        this.clientDao = clientDao;
        this.compteDao = compteDao;
        this.livretDao = livretDao;
    }

    @Override
    public void virement(long noCompteSource, long noCompteDestanation, Double montant) {
      Compte source = compteDao.find(noCompteSource);
      Compte destination = compteDao.find(noCompteDestanation);
      source.setSolde(source.getSolde() - montant);
      destination.setSolde(destination.getSolde() + montant);
      compteDao.edit(source);
      compteDao.edit(destination);
    }

    @Override
    public Collection<Compte> getComptesOfClient(long id) {
        Client c = this.getClient(id);
        c.getComptes().size();
        return c.getComptes();
    }

    @Override
    public void createClient(Client client) {
        clientDao.create(client);
    }


    @Override
    @Transactional(readOnly = true)
    public Client getClient(long id) {
        return clientDao.find(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Client> getAllClients() {
        return clientDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Compte> getAllComptes() {
        return compteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Livret> getAllLivrets() {
        return livretDao.findAll();
    }

    @Override
    public void saveClients(Client... clients) {
       for (Client client:clients){
           clientDao.create(client);
       }
    }

    @Override
    public void deleteClient(long id) {
        Client client = this.getClient(id);
        clientDao.remove(client);
    }
}
