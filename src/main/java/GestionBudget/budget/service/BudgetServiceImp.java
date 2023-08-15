package GestionBudget.budget.service;

import GestionBudget.budget.entite.Budget;
import GestionBudget.budget.entite.Categorie;
import GestionBudget.budget.entite.Depense;
import GestionBudget.budget.entite.Utilisateur;
import GestionBudget.budget.repository.RepositoryBudget;
import GestionBudget.budget.repository.RepositoryDepense;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor @NoArgsConstructor
public class BudgetServiceImp implements InterfaceBudget{

    @Autowired
    private RepositoryBudget repositoryBudget;
    @Autowired
    private RepositoryDepense repositoryDepense;
    @Autowired
    private InterfaceAlert alertServie;



    @Override
    public String creer(Budget budget) {
        Utilisateur utilisateur = budget.getUtilisateurBudget();
        Categorie categorie= budget.getCategorieBudget();
        LocalDate dateDebut = budget.getDateDebut();
        LocalDate dateDeFin = dateDebut.with(TemporalAdjusters.lastDayOfMonth());
        budget.setDateFin(dateDeFin);
        LocalDate dateToDate= LocalDate.now();

        Budget budgetVerif= null;

        Budget titreVerif = repositoryBudget.findByTitreBudget(budget.getTitreBudget());
        if (titreVerif!=null){
            throw new EntityNotFoundException("Le buget existe");
        }

        budgetVerif = repositoryBudget.findByUtilisateurBudgetAndCategorieBudgetAndDateFin(utilisateur, categorie, dateDeFin);
        if(budgetVerif!=null){
            throw new EntityNotFoundException("Ce budget existe deja!!! Il appartient a un autre utilisateur");
        }
        if(dateDebut.isAfter(dateToDate)){
            throw new EntityNotFoundException("Entrez une date valide");
        }
        if(dateDebut.getYear()!= dateToDate.getYear()){
            throw new EntityNotFoundException("L'annee  du budget est differente de l'année en cours");
        }
        int montantAlert= Math.toIntExact(budget.getMontantAlert());
        int montantPrincipal= Math.toIntExact(budget.getMontantBudget());

        if(montantAlert > montantPrincipal){
            throw new EntityNotFoundException("Le montant alert est superieur au montant principal");
        }

        LocalDate dateBudgetPrecedent = dateDeFin.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
        Budget budgetPrecedent = repositoryBudget.findByUtilisateurBudgetAndCategorieBudgetAndDateFin(utilisateur,categorie,dateDeFin);
        if (budgetPrecedent!=null){
            if (dateBudgetPrecedent.isBefore(dateToDate)){
                int montantRestantPrecedentBudget = Math.toIntExact(budgetPrecedent.getMontantRestant());
                if (montantRestantPrecedentBudget>0){
                    int montantActuelBudget = (int) (budget.getMontantBudget() + montantRestantPrecedentBudget);
                    int montantRestantActuelBudget = (int) (budget.getMontantRestant() + montantRestantPrecedentBudget);
                    budget.setMontantBudget((long) montantActuelBudget);
                    budget.setMontantRestant((long) montantRestantActuelBudget);
                }
            }
        }
        budget.setMontantRestant((long) montantPrincipal);
        repositoryBudget.save(budget);
        return "Budget creer";


      /*  LocalDate dateDebutVerif= budget.getDateDebut();
        if (dateDebutVerif.isAfter(LocalDate.now()) || dateDebutVerif.isBefore(LocalDate.now())){
            throw new EntityNotFoundException("Entre une date valide !");
        }  */
        //budget.setDateFin(dateDebutVerif.with(TemporalAdjusters.lastDayOfMonth()));

    }

    @Override
    public List<Budget> lire() {
        return repositoryBudget.findAll();
    }

    @Override
    public Budget afficher(Long idBudget) {
        return repositoryBudget.findById(idBudget).orElseThrow(
                () -> new EntityNotFoundException("Id non trouver")
        );
    }

    @Override
    public Budget modifier(Budget budget, Long idBudget) {
        return  repositoryBudget.findById(idBudget).orElseThrow(
                ()-> new EntityNotFoundException("Identifiant n'existe pas !")
        );
    }

    @Override
        public String supprimer(Long idBudget) {
            Budget budget = repositoryBudget.findByIdBudget(idBudget);
            if (budget==null){
                return "Identifiant incorrect";
            }else {
                repositoryBudget.deleteById(idBudget);
                return "Identifiant supprimer";
            }
    }

    @Override
    public void montantReduit(Depense depense) {
        int  montantDepense= depense.getMontantDepense();
        Budget budget= repositoryBudget.findByIdBudget(depense.getBudgetDepense().getIdBudget());
        int montanBudgettRestant= Math.toIntExact(budget.getMontantRestant());
        int montantAlert = Math.toIntExact(budget.getMontantAlert());
        if(montantDepense >= montanBudgettRestant){
            throw new EntityNotFoundException("Desole, votre solde est insuffisant pour cette depense");
        }
        int reste= montanBudgettRestant - montantDepense;
        budget.setMontantRestant((long) reste);
        // Declanchement de l'alert
        if (budget.getMontantRestant() <= montantAlert){
                 alertServie.envoieAlert(budget);
        }
        repositoryBudget.save(budget);


    }


    @Override
    public void modifieMontantBudget(Depense depense) {
        Budget depenseBudget = repositoryBudget.findByIdBudget(depense.getBudgetDepense().getIdBudget());
        Depense depenseVerif = repositoryDepense.findByIdDepense(depense.getIdDepense());
        int depenseMontant = depense.getMontantDepense();
        int depenseVerifMontant = depenseVerif.getMontantDepense();
        if (depenseMontant != depenseVerifMontant){
            int reste = depenseMontant - depenseVerifMontant;
            depenseBudget.setMontantRestant(depenseBudget.getMontantRestant()-reste);
            repositoryBudget.save(depenseBudget);
        }

    }

    @Override
    public void supprmerMontantBudget(Depense depense) {
        Budget budget = depense.getBudgetDepense();
        budget.setMontantRestant(budget.getMontantRestant() + depense.getMontantDepense());

    }


}
