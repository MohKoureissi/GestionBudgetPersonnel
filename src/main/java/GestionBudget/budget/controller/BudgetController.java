package GestionBudget.budget.controller;

import GestionBudget.budget.entite.Budget;
import GestionBudget.budget.service.BudgetServiceImp;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor @NoArgsConstructor
@RequestMapping("budget")
public class BudgetController {
    @Autowired
    private BudgetServiceImp budgetService;

    @PostMapping("/creer")
    private String creer(@Valid @RequestBody Budget budget){
        return budgetService.creer(budget);
    }

    @GetMapping("/lire")
    List<Budget> lire(){ return budgetService.lire();}

    @GetMapping("/afficher")
    private Budget afficher(@RequestParam Long idBudget){
        return budgetService.afficher(idBudget);
    }

    @PutMapping("/modifier")
    private Budget modifier(@RequestBody Budget budget , @RequestParam Long idBudget){
       return budgetService.modifier(budget, idBudget);
    }

    @DeleteMapping("/supprimer")
    private String supprimer(@RequestParam Long idBudget){
        budgetService.supprimer(idBudget);
        return "Budget supprimer";
    }

}
