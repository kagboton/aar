package fr.orleans.miage.aar.tp7.frontend.contreleurs;

import fr.orleans.miage.aar.tp7.backend.forms.RegistrationForm;
import fr.orleans.miage.aar.tp7.backend.modele.Client;
import fr.orleans.miage.aar.tp7.backend.modele.Compte;
import fr.orleans.miage.aar.tp7.backend.services.BanqueService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class InscriptionControleur {


    private BanqueService banqueService;
    private PasswordEncoder passwordEncoder;

    public InscriptionControleur(BanqueService banqueService, PasswordEncoder passwordEncoder) {
        this.banqueService = banqueService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping
    public String registrationForm(RegistrationForm registrationForm){
        return "registerForm";
    }

    @PostMapping
    public String processRegistration(@Valid RegistrationForm registrationForm, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "registerForm";
        }
        Client client = registrationForm.toUser(passwordEncoder);
        banqueService.creerClient(client);
        for (Compte compte : client.getComptes())
            banqueService.creerCompte(compte);
        return "redirect:/login";
    }
}
