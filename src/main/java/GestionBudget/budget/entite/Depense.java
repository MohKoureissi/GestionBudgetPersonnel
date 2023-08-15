package GestionBudget.budget.entite;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Depense {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDepense;

    @Column(name = "montantDepense")
    private int montantDepense;

    @Column(name = "description")
    @NotNull(message = "Remplissez champs vides")
    @Size(max = 50, message = "Text trop long")
    private String description;

    @Column(name = "dateDepense")
    @NotNull(message = "Remplissez champs vides")
    private LocalDate dateDepense;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Budget budgetDepense;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TypeDepense typeDepense;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Utilisateur utilisateurDepense;
}
