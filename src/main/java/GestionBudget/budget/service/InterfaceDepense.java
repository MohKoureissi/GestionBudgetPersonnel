package GestionBudget.budget.service;

import GestionBudget.budget.entite.Depense;

import java.util.List;
import java.util.Optional;

public interface InterfaceDepense {

    Depense creer(Depense depense);

    List<Depense> lire();

    String modiffier( Depense depense);

    String supprimer(Long idDepense);
}
