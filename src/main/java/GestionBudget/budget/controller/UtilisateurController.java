package GestionBudget.budget.controller;

import GestionBudget.budget.entite.Utilisateur;
import GestionBudget.budget.service.UtilisateurServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("utilisateur")
public class UtilisateurController {
    private UtilisateurServiceImpl utilisateurService;

    @PostMapping("/inscrire")
   private String inscrire(@RequestBody Utilisateur utilisateur){
       return utilisateurService.inscrire(utilisateur);
    }

    @PostMapping("/connexion")
    private String connexion(@RequestParam String email,@RequestParam String motDePasse){
        return utilisateurService.connexion(email,motDePasse);
    }

    @GetMapping("/lire")
    private List<Utilisateur> lire(){
      return utilisateurService.lire();
    }

    @GetMapping("/afficher")
    private Utilisateur afficher(@RequestParam Long idUtilisateur){
        return utilisateurService.afficher(idUtilisateur);
    }

    @PutMapping("/modifier")
    private String modifier(@RequestBody Utilisateur utilisateur){
        return utilisateurService.modifier(utilisateur);
    }

    @DeleteMapping("/supprimer")
    private String supprimer(@RequestParam Long idUtilisateur){
        return utilisateurService.supprimer(idUtilisateur);
    }


}
