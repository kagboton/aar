package fr.orleans.miage.aar.tp7.backend.services;

import fr.orleans.miage.aar.tp7.backend.modele.Client;
import fr.orleans.miage.aar.tp7.backend.modele.Compte;
import fr.orleans.miage.aar.tp7.backend.modele.Livret;
import fr.orleans.miage.aar.tp7.backend.repositories.ClientRepository;
import fr.orleans.miage.aar.tp7.backend.repositories.CompteRepository;
import fr.orleans.miage.aar.tp7.backend.repositories.LivretRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;


@Service
@Transactional
public class BanqueServiceImpl implements BanqueService {

    private final ClientRepository clientRepository;
    private final CompteRepository compteRepository;
    private final LivretRepository livretRepository;

    public BanqueServiceImpl(ClientRepository clientRepository, CompteRepository compteRepository, LivretRepository livretRepository) {
        this.clientRepository = clientRepository;
        this.compteRepository = compteRepository;
        this.livretRepository = livretRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Livret> getAllLivrets() {
        return livretRepository.findAll();
    }

    @Override
    public void creerClient(Client c) {
        clientRepository.save(c);
    }

    @Override
    public void creerCompte(Compte compte) {
        compteRepository.save(compte);
    }

    @Override
    public void deleteClientById(long id) {
        clientRepository.deleteById(id);
    }


    @Override
    public void virement(long compteOrigin, long compteDest, Double somme) {
        Optional<Compte> source = compteRepository.findById(compteOrigin);
        Optional<Compte> destination = compteRepository.findById(compteDest);

        if(!source.isPresent() && !destination.isPresent()){
            throw new RuntimeException("Comptes source ou destination inexistants");
        }

        Compte s = source.get();
        Compte d = destination.get();

        s.setSolde(s.getSolde()-somme);
        d.setSolde(s.getSolde()+somme);

    }

    @Override
    @Transactional(readOnly = true)
    public Client getClientById(long id) {
        return clientRepository.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public Client getClientByUSername(String username){
        return clientRepository.findByUsername(username).get(0);
    }

    @Override
    public Collection<Compte> getCompteOfClient(long id) {
        Collection<Compte> comptes = clientRepository.findById(id).get().getComptes();
        comptes.size();
        return comptes;
    }



}
