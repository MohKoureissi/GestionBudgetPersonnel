package GestionBudget.budget.repository;

import GestionBudget.budget.entite.Budget;
import GestionBudget.budget.entite.Depense;
import GestionBudget.budget.entite.TypeDepense;
import GestionBudget.budget.entite.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface RepositoryDepense extends JpaRepository<Depense,Long> {

    Depense findByDescription(String description);
    Depense findByIdDepense(Long idDepense);
    Depense findByUtilisateurDepenseAndBudgetDepenseAndTypeDepenseAndDateDepense(Utilisateur utilisateur, Budget budget, TypeDepense typeDepense, LocalDate date);
    Depense findFirstByUtilisateurDepenseAndBudgetDepenseAndTypeDepenseOrderByDateDepenseDesc(Utilisateur utilisateur, Budget budget, TypeDepense typeDepense);
}
