package GestionBudget.budget.service;

import GestionBudget.budget.entite.Alert;
import GestionBudget.budget.entite.Budget;
import GestionBudget.budget.entite.Categorie;
import GestionBudget.budget.entite.Utilisateur;
import GestionBudget.budget.repository.RepositoryAlert;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AlertServiceImp implements InterfaceAlert{
    @Autowired
    private RepositoryAlert repositoryAlert;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public Alert save(Alert alert) {
        return null;
    }

    @Override
    public void envoieAlert(Budget budget) {
        Utilisateur utilisateur= budget.getUtilisateurBudget();
        Categorie categorie = budget.getCategorieBudget();
        int montantAlert = Math.toIntExact(budget.getMontantAlert());
        Alert alert = new Alert();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String sender= "koureissi89@gmail.com";
        String message= null;
        if(montantAlert==0){
         message= "Mr/Mme"+utilisateur.getPrenom()+" "+utilisateur.getNom()+" Votre Budget de "+budget.getMontantBudget()+
                 "de la categorie "+categorie.getNomCategorie()+" est de 0 FCFA";
        }
        else {
            message= "Mr/Mme"+utilisateur.getPrenom()+" "+utilisateur.getNom()+" Votre Budget de "+budget.getMontantBudget()+
                    "de la categorie "+categorie.getNomCategorie()+" A atteint le seuil de votre montant Alert qui est "+budget.getMontantAlert();
        }
       alert.setTexte(message);

        try {

            mailMessage.setFrom(sender);
            mailMessage.setTo(utilisateur.getEmail());
            mailMessage.setText(alert.getTexte());
            mailMessage.setSubject("Alert: Gestion de budget personnel !");
            javaMailSender.send(mailMessage);

            alert.setTexte(message);
            repositoryAlert.save(alert);

        }catch (Exception e){
            throw new EntityNotFoundException(e.getMessage());
        }
    }
}
