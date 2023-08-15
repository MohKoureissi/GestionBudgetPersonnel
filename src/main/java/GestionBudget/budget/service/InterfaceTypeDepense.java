package GestionBudget.budget.service;

import GestionBudget.budget.entite.TypeDepense;

import java.util.List;

public interface InterfaceTypeDepense {

    String creer(TypeDepense typeDepense);
    TypeDepense  afficher(Long idTypeDepense);
    List<TypeDepense> lire();
    TypeDepense modifier(Long idTypeDepense, TypeDepense typeDepense);
    String supprimer(Long idTypeDepense);
}
