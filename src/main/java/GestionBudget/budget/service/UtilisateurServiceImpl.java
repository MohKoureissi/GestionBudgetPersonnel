package GestionBudget.budget.service;

import GestionBudget.budget.entite.Utilisateur;
import GestionBudget.budget.repository.RepositoryUtilisateur;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements InterfaceUtilisateur{
    @Autowired
    private RepositoryUtilisateur repositoryUtilisateur;

    public UtilisateurServiceImpl() {
    }

    @Override
    public String inscrire(Utilisateur utilisateur) {
        Utilisateur utilisateur1 = repositoryUtilisateur.findByEmail(utilisateur.getEmail());
        if (utilisateur1 ==null){
            repositoryUtilisateur.save(utilisateur);
            return "Utilisateur inscrit";
        }
        throw new EntityNotFoundException("Utilisateur existe deja");

    }

    @Override
    public String connexion(String email, String motDePasse) {
        Utilisateur utilisateur= repositoryUtilisateur.findByEmailAndMotDePasse(email,motDePasse);
        if (utilisateur!= null){
           return "Utilisateur connecter";
        }
        throw new EntityNotFoundException("Utilisateur n'existe pas");
    }

    @Override
    public List<Utilisateur> lire() {
        return repositoryUtilisateur.findAll();
    }

    @Override
    public Utilisateur afficher(Long idUtilisateur) {
        return repositoryUtilisateur.findById(idUtilisateur).orElseThrow(
                ()-> new EntityNotFoundException("Id Utilisateur non trouver")
        );
    }

    @Override
    public String modifier(Utilisateur utilisateur) {
        Utilisateur utilisateur1 = repositoryUtilisateur.findByEmail(utilisateur.getEmail());
        if (utilisateur1!=null){
            repositoryUtilisateur.save(utilisateur);
            return "Utilisateur modifier !";
        }else {
            return "Utilisateur existe deja !";
        }

    }


    @Override
    public String supprimer(Long idUtilisateur) {
        Utilisateur utilisateur = repositoryUtilisateur.findByIdUtilisateur(idUtilisateur);
        if(utilisateur!=null){
            repositoryUtilisateur.delete(utilisateur);
            return "Utilisateur supprimer";
        }
        throw new EntityNotFoundException("Utilisateur n'existe pas");
    }
}
