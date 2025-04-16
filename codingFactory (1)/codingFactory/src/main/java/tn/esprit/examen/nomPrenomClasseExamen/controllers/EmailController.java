package tn.esprit.examen.nomPrenomClasseExamen.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.examen.nomPrenomClasseExamen.entities.EventStatus;
import tn.esprit.examen.nomPrenomClasseExamen.services.EmailService;
import tn.esprit.examen.nomPrenomClasseExamen.services.ParticipantService;

import java.util.List;
@RestController
@RequestMapping("/api/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private ParticipantService participantService;

  @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            // Log de débogage
            System.out.println("Données reçues pour l'envoi d'email: " + emailRequest.getId());

            emailService.sendEmail(emailRequest.getEmails(), emailRequest.getSubject(), emailRequest.getMessage());

            Boolean isUpdated = participantService.updateParticipantStatus(emailRequest.getId(), EventStatus.APPROVED);
            return "Emails envoyés avec succès";
        } catch (Exception e) {
            // Log de l'exception
            e.printStackTrace();
            return "Erreur lors de l'envoi des emails : " + e.getMessage();
        }/*
    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestParam("emails") String emails,
                                            @RequestParam("pdf") MultipartFile pdf) {
        try {
            // Convertir le JSON des emails en une liste
            ObjectMapper objectMapper = new ObjectMapper();
            List<String> emailList = objectMapper.readValue(emails, List.class);

            // Appeler le service pour envoyer l'email avec la pièce jointe
            emailService.sendEmailWithAttachment(emailList, pdf);

            return ResponseEntity.ok("Email envoyé avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur lors de l'envoi de l'email.");
        }
    }*/
}}
