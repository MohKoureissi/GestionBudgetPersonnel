package GestionBudget.budget.controller;

import GestionBudget.budget.entite.TypeDepense;
import GestionBudget.budget.service.TypeDepenseServiceImp;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor @NoArgsConstructor
@RequestMapping("typeDepense")
public class TypeDepenseContoller {
    @Autowired
    private TypeDepenseServiceImp typeDepenseService;

    @PostMapping("/creer")
    private String creer(@RequestBody TypeDepense typeDepense){
        return typeDepenseService.creer(typeDepense);
    }

    @GetMapping("/lire")
    List<TypeDepense>lire(){
        return typeDepenseService.lire();
    }

    @GetMapping("/afficher")
    private TypeDepense afficher(@RequestBody @RequestParam Long idTypeDepense){
        return typeDepenseService.afficher(idTypeDepense);
    }

    @PutMapping("/modifier")
    private TypeDepense modifier(@RequestBody TypeDepense typeDepense, Long idTypeDepense){
        return typeDepenseService.modifier(idTypeDepense,typeDepense);
    }

    @DeleteMapping("/supprimer")
    private String supprimer(@RequestParam Long idTypeDepense){
        return typeDepenseService.supprimer(idTypeDepense);
    }
}
