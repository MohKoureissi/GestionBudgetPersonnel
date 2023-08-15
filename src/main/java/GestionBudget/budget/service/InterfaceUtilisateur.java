package GestionBudget.budget.service;

import GestionBudget.budget.entite.Utilisateur;

import java.util.List;

public interface InterfaceUtilisateur {

    String inscrire(Utilisateur utilisateur);      // Creer utilisateur

    String connexion(String email, String motDePasse);    // Connexion de l'utilisateur

    List<Utilisateur> lire();               //Afficher touts les utilisateur

    Utilisateur afficher(Long idUtilisateur);          // Recgercher utilisateur par nom

    String modifier(Utilisateur utilisateur);            //Modifier Utilisateur

    String supprimer(Long idUtilisateur);            //Supprimer utilisateur
}
