package GestionBudget.budget.service;

import GestionBudget.budget.entite.Alert;
import GestionBudget.budget.entite.Budget;

public interface InterfaceAlert {
    Alert save(Alert alert);

    void envoieAlert(Budget budget);
}
