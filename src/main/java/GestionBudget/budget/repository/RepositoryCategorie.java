package GestionBudget.budget.repository;

import GestionBudget.budget.entite.Categorie;
import GestionBudget.budget.entite.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCategorie extends JpaRepository<Categorie,Long> {
    Categorie findByNomCategorie(String nomCategorie);
}
