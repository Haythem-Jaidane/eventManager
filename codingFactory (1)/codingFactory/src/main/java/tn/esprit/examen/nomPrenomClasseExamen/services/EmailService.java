package tn.esprit.examen.nomPrenomClasseExamen.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(List<String> to, String subject, String htmlContent) {
        try {
            // Create a MimeMessage to send the email
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true); // "true" means we are sending HTML email

            // Set the recipients of the email
            messageHelper.setTo(to.toArray(new String[0]));  // Convert List to array
            messageHelper.setSubject(subject);
            messageHelper.setFrom("hazemkechiche@gmail.com"); // Replace with your email
            messageHelper.setText(htmlContent, true); // The second argument "true" means the content is HTML

            // Send the email
            javaMailSender.send(mimeMessage);

            System.out.println("Emails sent successfully");
            System.out.println("HTML reçu : " + htmlContent);
        } catch (MailException e) {
            e.printStackTrace();
            System.out.println("Error while sending email");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendEmailWithAttachment(List<String> toEmails, MultipartFile file) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Ajouter les destinataires
        for (String email : toEmails) {
            helper.addTo(email);
        }

        // Ajouter le sujet et le corps de l'email
        helper.setSubject("Informations sur le participant");
        helper.setText("Veuillez trouver ci-joint les informations du participant avec le QR code.");

        // Ajouter la pièce jointe
        helper.addAttachment("participant_info.pdf", file);

        // Envoyer l'email
        javaMailSender.send(message);
    }
}
