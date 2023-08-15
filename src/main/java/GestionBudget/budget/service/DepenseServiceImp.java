package GestionBudget.budget.service;

import GestionBudget.budget.entite.Budget;
import GestionBudget.budget.entite.Depense;
import GestionBudget.budget.entite.TypeDepense;
import GestionBudget.budget.entite.Utilisateur;
import GestionBudget.budget.execption.DuplicateException;
import GestionBudget.budget.repository.RepositoryDepense;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor @NoArgsConstructor
public class DepenseServiceImp implements InterfaceDepense{
    @Autowired
    private BudgetServiceImp budgetService;
    @Autowired
    private RepositoryDepense repositoryDepense;

    @Override
    public Depense creer(Depense depense) {
        LocalDate dateDepense = depense.getDateDepense();            //Recuration de la date du depense
        LocalDate dateDebut = depense.getBudgetDepense().getDateDebut();
        TypeDepense typeDepense = depense.getTypeDepense();     //Recuperation du type de depense
        Utilisateur utilisateurDepense = depense.getUtilisateurDepense();     // Recuperation de l'utilisateur effectuant la depense
        Budget budgetDepense = depense.getBudgetDepense();   // Budget courant associer à la depense
        int montantDepense = depense.getMontantDepense();

        Depense depenseTest= null;
        //Si la date a la quelle on effectue la depense est avant celle de la date de debut du budget
        // Ou si la date de depense est apres celle de la fin du budget
       // if(depenseTest.getDateDepense().isBefore(dateDebut) || depenseTest.getDateDepense().isAfter((LocalDate.now()))){
      //      throw new EntityNotFoundException("Le budget specifier pour votre depense n'est pas valide! verifier votre date");
       // }
        switch (typeDepense.getNomTypeDepense()){
            case "quotidient":
           depenseTest = repositoryDepense.findByDescription(depense.getDescription());
           if(depenseTest !=null)
               throw new EntityNotFoundException("Vous avez deja effectuer votre depense quotidienne");

           budgetService.montantReduit(depense);
           break;

        case "hebdomadaire":
        //    depenseTest = repositoryDepense.findFirstByUtilisateurDepenseAndBudgetDepenseAndTypeDepenseOrderByDateDepenseDesc(utilisateurDepense, budgetDepense,typeDepense);
            depenseTest= repositoryDepense.findByDescription(depense.getDescription());
            if(depenseTest !=null){
                if(depenseTest.getDateDepense().plusDays(7).isAfter(LocalDate.now()))    //On prend la date a la quelle on fait la depense et on ajoute 7 jours pour que les depense hebdo ne se chevoche pas
                    throw new EntityNotFoundException("Vous avez deja effectuer votre depense hebdomadaire");
                    budgetService.montantReduit(depense);
                    break;
            }

            case"mensuel":
              //  depenseTest = repositoryDepense.findFirstByUtilisateurDepenseAndBudgetDepenseAndTypeDepenseDepenseOrderByDateDepenseDesc(utilisateurDepense, budgetDepense, typeDepense);
                depenseTest = repositoryDepense.findByDescription(depense.getDescription());
                if(depenseTest != null){
                    if(depenseTest.getDateDepense().plusDays(30).isAfter(LocalDate.now()))     // ici on ajoute 30 jours comme le mois
                        throw new EntityNotFoundException("Vous avez deja effectuer votre depense mensuel");
                         budgetService.montantReduit(depense);
                        break;
                }

            default:
                throw new EntityNotFoundException("Ce type de depense n'existe pas");
             }
             repositoryDepense.save(depense);
        return repositoryDepense.findByIdDepense(depense.getIdDepense());
    }
    @Override
    public List<Depense> lire() {
        return repositoryDepense.findAll();
    }

    @Override
    public String  modiffier( Depense depense) {
        Depense depense1 = repositoryDepense.findByDescription(depense.getDescription());
        if (depense1==null ){
            throw new EntityNotFoundException("Depense existe deja");
        }
        budgetService.modifieMontantBudget(depense);
        repositoryDepense.save(depense);
        return "depense modifier";
    }

    @Override
    public String supprimer(Long idDepense) {
        Depense depense =repositoryDepense.findByIdDepense(idDepense);
        if (depense!=null){
            budgetService.supprmerMontantBudget(depense);
            repositoryDepense.deleteById(idDepense);
            return "depense supprimer";
        } throw new DuplicateException("depense avec ID "+idDepense+" n'existe pas");
    }
}
