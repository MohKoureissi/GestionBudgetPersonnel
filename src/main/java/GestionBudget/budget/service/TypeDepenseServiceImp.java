package GestionBudget.budget.service;

import GestionBudget.budget.entite.TypeDepense;
import GestionBudget.budget.entite.Utilisateur;
import GestionBudget.budget.repository.RepositoryTypeDepense;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor @NoArgsConstructor
public class TypeDepenseServiceImp implements InterfaceTypeDepense {
    private RepositoryTypeDepense repositoryTypeDepense;

    @Override
    public String creer(TypeDepense typeDepense) {
        TypeDepense typeDepense1= repositoryTypeDepense.findByNomTypeDepense(typeDepense.getNomTypeDepense());
        if(typeDepense1 == null){
            repositoryTypeDepense.save(typeDepense);
            return "TypeDepense enregistrer";
        }
        throw new EntityNotFoundException("Type depense existe deja");
    }

    @Override
    public TypeDepense afficher(Long id) {
        return null;
    }

    @Override
    public List<TypeDepense> lire() {
        return repositoryTypeDepense.findAll();
    }

    @Override
    public TypeDepense modifier(Long idTypeDepense, TypeDepense typeDepense) {
        return repositoryTypeDepense.findById(idTypeDepense).orElseThrow(
                () -> new EntityNotFoundException("Type de depense non trouvé")
        );
    }

    @Override
    public String supprimer(Long idTypeDepense) {
         TypeDepense typeDepense = repositoryTypeDepense.findByIdTypeDepense(idTypeDepense);
        if(typeDepense!=null){
            repositoryTypeDepense.delete(typeDepense);
            return "Utilisateur supprimer";
        }
        throw new EntityNotFoundException("Utilisateur n'existe pas");
    }
}
