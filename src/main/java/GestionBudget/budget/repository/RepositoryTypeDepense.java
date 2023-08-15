package GestionBudget.budget.repository;

import GestionBudget.budget.entite.TypeDepense;
import GestionBudget.budget.entite.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryTypeDepense extends JpaRepository<TypeDepense ,Long> {
    TypeDepense findByNomTypeDepense(String idTypeDepense);
    TypeDepense findByIdTypeDepense(Long idTypeDepense);

}
