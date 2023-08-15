package GestionBudget.budget.entite;

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
@Table(name = "typeDepense")
public class TypeDepense {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeDepense;

    @Column(name ="nomTypeDepense" )
    @NotNull(message = "Remplissez les champs vides")
    @Size(max = 50, message = "Text trop long")
    private String nomTypeDepense;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Utilisateur utilisateurTypeDepense;

    @OneToMany(mappedBy = "typeDepense")
    List<Depense> depenseTypeDepense;
}
