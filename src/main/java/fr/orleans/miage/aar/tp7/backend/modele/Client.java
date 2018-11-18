package fr.orleans.miage.aar.tp7.backend.modele;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Data
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
@ToString(exclude={"comptes"})
@Entity
public class Client implements UserDetails {

    /**
     * Attributs de la classe
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String nom;
    private String prenom;
    private String adresse;
    private boolean enabled; //Compte actif ou pas

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "client_roles")
    private Set<String> roles;

    @OneToMany(mappedBy = "titulaire", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Compte> comptes;

    /**
     * Constructeur avec les paramètres. Chaque client créé est activé est a le rôle "ROLE_USER"
     * Le constructeur par défaut est généré par lombok
     * @param username
     * @param password
     * @param nom
     * @param prenom
     * @param adresse
     */
    public Client(String username, String password, String nom, String prenom, String adresse) {
        this.username = username;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.comptes = new ArrayList<>();
        this.roles = new HashSet<>();
        roles.add("ROLE_USER");
        this.enabled = true;
    }

    /**
     * Implémentation des méthodes de UserDetails
     *
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addCompte(Compte compte) {
        this.comptes.add(compte);
    }

    public  void addRole(String role){this.roles.add(role);}

}
