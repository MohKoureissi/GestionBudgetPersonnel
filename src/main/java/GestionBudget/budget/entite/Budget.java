package GestionBudget.budget.entite;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "budget")
public class Budget {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBudget;

    @Column(name = "titreBudget")
    @NotNull(message = "Remplissez les champs vides")
    @Size(max = 50, message = "Text trop long")
    private String titreBudget;

    @Column(name = "montantBudget")
    @NotNull(message = "Remplissez les champs vides")
    //@Size(max = 150, message = "Montant trop elevé")
    private Long montantBudget;

    @Column(name = "montantRestant")
    @NotNull(message = "Remplissez les champs vides")
    //@Size(max = 150, message = "Montant trop elevé")
    private Long montantRestant;

    @Column(name = "montantAlert")
    @NotNull(message = "Remplissez les champs vides")
    //@Size(max = 150, message = "Montant trop elevé")
    private Long montantAlert;

    @Column(name = "dateDebut")
    @NotNull(message = "Remplissez les champs vides")
    private LocalDate dateDebut;

    @Column(name = "dateFin")
    @NotNull(message = "Remplissez les champs vides")
    private LocalDate dateFin;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Categorie categorieBudget;

    @ManyToOne
    @NotNull(message = "Utilisateur ne peut pas etre null")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Utilisateur utilisateurBudget;

 /*
    @OneToMany(mappedBy = "budgetDepense")
      /* List<Depense> depenseBudget;

    @OneToMany(mappedBy = "budgetAlert")
   /* List<Alert> AlertBudget; */
}
