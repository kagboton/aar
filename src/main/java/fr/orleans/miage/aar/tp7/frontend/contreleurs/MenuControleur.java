package fr.orleans.miage.aar.tp7.frontend.contreleurs;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuControleur {

    //TODO : I can replace this controller by a view controller (see Listing 2.15 of Spring in action book)

    /**
     * Accès au menu
     * @return la page d'accueil
     */
    @GetMapping
    public String menu(Model model){
        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        String username = aut.getName(); //Récupère le username de celui qui est loggé
        model.addAttribute("name", username);
        return "menu";
    }
}
