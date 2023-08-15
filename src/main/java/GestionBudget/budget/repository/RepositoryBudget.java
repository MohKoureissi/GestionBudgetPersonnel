package GestionBudget.budget.repository;

import GestionBudget.budget.entite.Budget;
import GestionBudget.budget.entite.Categorie;
import GestionBudget.budget.entite.Depense;
import GestionBudget.budget.entite.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface RepositoryBudget extends JpaRepository<Budget,Long> {
    Budget findByTitreBudget(String titreBudget);

    Budget findByDateDebutAndDateFin(LocalDate dateDebut, LocalDate dateFin);
    Budget findByIdBudget(Long idBudget);
    Budget findByUtilisateurBudgetAndCategorieBudgetAndDateFin(Utilisateur utilisateur, Categorie categorie, LocalDate dateFin);


}
