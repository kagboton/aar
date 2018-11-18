package fr.orleans.miage.aar.tp7.frontend.contreleurs;


import fr.orleans.miage.aar.tp7.backend.modele.Client;
import fr.orleans.miage.aar.tp7.backend.modele.Compte;
import fr.orleans.miage.aar.tp7.backend.modele.Livret;
import fr.orleans.miage.aar.tp7.backend.forms.VirementForm;
import fr.orleans.miage.aar.tp7.backend.services.BanqueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;


@Slf4j
@Controller
@RequestMapping("/")
public class BanqueControleur {

    @Autowired
    private BanqueService banqueService;


    /**
     * Liste de tous les clients de la banque
     * @return la page clients
     */
    @GetMapping(value = "clients")
    public String clients(Model model){
        model.addAttribute("clients", banqueService.getAllClients());
        return "clients";
    }

    /**
     * Accès à la page de selection d'un client
     * @return le formumlaire de selection d'un client
     */
    @GetMapping(value = "client")
    public String getClient(Model model){
        model.addAttribute("clients", banqueService.getAllClients());
        model.addAttribute("client", new Client());
        return "clientForm";
    }

    /**
     * Retrouve les informations d'un client
     * @param model
     * @param request
     * @return
     */
    @PostMapping(value = "client")
    public String processGetClient(Model model, WebRequest request){

        long cId = Long.parseLong(request.getParameter("clientId"));

        if(cId==0){
            model.addAttribute("error", "Invalid input") ;
            model.addAttribute("clients", banqueService.getAllClients());
            model.addAttribute("client", new Client());
            return "clientForm";
        }

        Iterable<Compte> allComptes = banqueService.getCompteOfClient(cId);
        Collection<Compte> comptes = new ArrayList<>();
        Collection<Livret> livrets = new ArrayList<>();

        for(Compte compte : allComptes){
            if(compte.getClass().equals(Livret.class)){
               livrets.add((Livret) compte);
            }else {
                comptes.add(compte);
            }
        }
        model.addAttribute("comptes", comptes);
        model.addAttribute("livrets", livrets);
        model.addAttribute("client", banqueService.getClientById(cId));

        return "client";
    }

    @GetMapping(value = "virement")
    public String virement(Model model, VirementForm virementForm){
        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        String userrname = aut.getName();
        Client client = banqueService.getClientByUSername(userrname);
        model.addAttribute("allComptes", banqueService.getAllComptes());
        model.addAttribute("clienrComptes", banqueService.getCompteOfClient(client.getId()));
        return "virementForm";
    }

    @PostMapping(value = "virement")
    public String processVirement(Model model, @Valid VirementForm virementForm, BindingResult bindingResult, WebRequest request){

        long sourceId = Long.parseLong(request.getParameter("sourceId"));
        long destinataireId = Long.parseLong(request.getParameter("destinataireId"));
        Double montant = Double.parseDouble(request.getParameter("montant"));


        if(sourceId==0 || destinataireId==0 || montant ==0){
            model.addAttribute("error", "Invalid input") ;
            return this.virement(model,virementForm);
        }

        if(sourceId==destinataireId){
            model.addAttribute("error", "Virement impossible") ;
            return this.virement(model,virementForm);
        }


        banqueService.virement(sourceId,destinataireId,montant);

        model.addAttribute("message", "Virement passé avec succès");
        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        String username = aut.getName(); //Récupère le username de celui qui est loggé
        model.addAttribute("name", username);

        return "menu";
    }

}
