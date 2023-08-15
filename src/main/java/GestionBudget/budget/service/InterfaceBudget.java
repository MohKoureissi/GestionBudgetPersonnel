package GestionBudget.budget.service;

import GestionBudget.budget.entite.Budget;
import GestionBudget.budget.entite.Depense;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface InterfaceBudget {

    String creer(Budget budget);

    List<Budget> lire();

    Budget afficher(Long idBudget);

    Budget modifier(Budget budget, Long idBudget);

    String supprimer(Long idBudget);

    void montantReduit(Depense depense);

    void modifieMontantBudget(Depense depense);

    void supprmerMontantBudget(Depense depense);
}
