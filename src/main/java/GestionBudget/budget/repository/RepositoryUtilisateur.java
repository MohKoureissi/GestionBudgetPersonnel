package GestionBudget.budget.repository;

import GestionBudget.budget.entite.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositoryUtilisateur extends JpaRepository<Utilisateur ,Long> {
    Utilisateur findByIdUtilisateur(Long idUtilisateur);
    Utilisateur findByEmail(String email);
    Utilisateur findByEmailAndMotDePasse(String email, String motDePasse);
}
