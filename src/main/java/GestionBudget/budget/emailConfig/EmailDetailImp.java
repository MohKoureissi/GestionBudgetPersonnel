package GestionBudget.budget.emailConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailDetailImp implements InterfaceEmail{
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("koureissi89@gmail.com") private String sender;
    @Override
    public String sendSimpleMail(EmailDetail details) {
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getDestinateur());
            mailMessage.setText(details.getMsg());
            mailMessage.setSubject(details.getObjet());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Email envoye avec succes";
        }
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }
}
