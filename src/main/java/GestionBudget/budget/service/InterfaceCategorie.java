package GestionBudget.budget.service;

import GestionBudget.budget.entite.Categorie;

import java.util.List;

public interface InterfaceCategorie {

    String ajouter(Categorie categorie);

    List<Categorie> lire();

    Categorie afficher(Long idCategie);

    Categorie mofifier(Long idCategorie, Categorie categorie);

    String supprimer(String nomCategorie);
}
