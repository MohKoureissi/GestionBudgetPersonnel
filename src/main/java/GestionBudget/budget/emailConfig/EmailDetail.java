package GestionBudget.budget.emailConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class EmailDetail {

    private String destinateur;
    private String msg;
    private String objet;
}
