package GestionBudget.budget.entite;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "Utilisateur")
public class Utilisateur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;

    @Column(name = "nom")
    @NotNull(message = "Remplissez les champs vides")
    @Size(max= 50, message = "Text trop long")
    private String nom;

    @Column(name = "prenom")
    @NotNull(message = "Remplissez les champs vides")
    @Size(max = 50, message = "Text trop long")
    private String prenom;

    @Column(name = "email")
    @NotNull(message = "Remplissez les champs vides")
    @Size(max = 50, message = "Text trop long")
    private String email;

    @Column(name = "motDePasse")
    @NotNull(message = "Remplissez les champs vides")
    @Size(max = 50, message = "Text trop long")
    private String motDePasse;
/*
    @OneToMany(mappedBy = "utilisateurCategorie")
    List<Categorie> categorieUtilisateur;

    @OneToMany(mappedBy = "utilisateurBudget")
    List<Budget> budgetUtilisateur;

    @OneToMany(mappedBy = "utilisateurDepense")
    List<Depense> depenseUtilisateur;

    @OneToMany(mappedBy = "utilisateurTypeDepense")
    List<TypeDepense> typeDepenseUtilisateur;

    @OneToMany(mappedBy = "utilisateurAlert")
    List<Alert> alertUtilisateur;
*/

}
