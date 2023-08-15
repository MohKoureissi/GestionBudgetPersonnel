package GestionBudget.budget.controller;

import GestionBudget.budget.entite.Depense;
import GestionBudget.budget.service.DepenseServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("depense")
public class DepenseController {
    @Autowired
    private DepenseServiceImp depenseService;

    @PostMapping("/creer")
    private Depense creer(@RequestBody Depense depense){
        return depenseService.creer(depense);
    }

    @GetMapping("/lire")
    List<Depense> lire(){
        return depenseService.lire();
    }

    @PutMapping("/modifier")
    private String modifier( Depense depense){
        return depenseService.modiffier(depense);
    }

    private String supprimer(Long idDepense){
        return depenseService.supprimer(idDepense);
    }
}
