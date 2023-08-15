package GestionBudget.budget.service;

import GestionBudget.budget.entite.Categorie;
import GestionBudget.budget.repository.RepositoryCategorie;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategorieServiceImp implements InterfaceCategorie{

    private RepositoryCategorie repositoryCategorie;
    @Override
    public String ajouter(Categorie categorie) {
        Categorie categorie1= repositoryCategorie.findByNomCategorie(categorie.getNomCategorie());
            if(categorie1 ==null){
                repositoryCategorie.save(categorie);
                return "Categorie Ajouter";
            }
            throw new EntityNotFoundException("Categorie existe deja");
        }


    @Override
    public List<Categorie> lire() {
        return repositoryCategorie.findAll();
    }

    @Override
    public Categorie afficher(Long idCategie) {
        return repositoryCategorie.findById(idCategie).orElseThrow(
                () -> new EntityNotFoundException("Id categorie n'existe pas")
        );
    }

    @Override
    public Categorie mofifier(Long idCategorie, Categorie categorie) {
        return repositoryCategorie.findById(idCategorie)
                .map(c-> {
                    c.setNomCategorie(categorie.getNomCategorie());
                    return repositoryCategorie.save(c);
                }).orElseThrow(()-> new RuntimeException(("Categorie modifier")));
    }

    @Override
    public String supprimer(String nomCategorie) {
        Categorie categorie = repositoryCategorie.findByNomCategorie(nomCategorie);
        if (categorie != null) {
            return "Categorie supprimer";
        }
        throw new EntityNotFoundException("Categorie n'existe pas");

    }
}
