package GestionBudget.budget.entite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "categorie")
public class Categorie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategorie;

    @Column(name = "nomCategorie")
    //@NotNull(message = "Remplissez champs vides")
   // @Size(max = 50, message = "Text trop long")
    private String nomCategorie;

    @ManyToOne
   // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Utilisateur utilisateurCategorie;

    @OneToMany(mappedBy = "categorieBudget")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<Budget> budgetCategorie;
}
