package GestionBudget.budget.repository;

import GestionBudget.budget.entite.Alert;
import GestionBudget.budget.entite.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryAlert extends JpaRepository<Alert ,Long> {
    Alert findByIdAlert(Long idAlert);


}
