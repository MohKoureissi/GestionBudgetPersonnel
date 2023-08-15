package GestionBudget.budget.controller;

import GestionBudget.budget.entite.Categorie;
import GestionBudget.budget.service.CategorieServiceImp;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("categorie")
public class CategorieController {
    private CategorieServiceImp categorieService;

    @PostMapping("/ajouter")
    private String ajouter(@RequestBody Categorie categorie){
        return categorieService.ajouter(categorie);
    }

    @GetMapping("/lire")
    List<Categorie> lire(){
        return categorieService.lire();
    }

    @GetMapping("/afficher")
    public Categorie afficher(@RequestParam Long idCategorie){
        return categorieService.afficher(idCategorie);
    }

    @PutMapping("/modifier{idCategorie}")
    public Categorie modifier(@PathVariable Long idCategorie,  @RequestBody Categorie categorie){
        return categorieService.mofifier(idCategorie,categorie );
    }
    @DeleteMapping("/supprimer{idCategorie}")   public String delete(@PathVariable String nomCategorie){
        return categorieService.supprimer(nomCategorie);
    }

}
