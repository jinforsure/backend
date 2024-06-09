package com.example.pfe.Controller;


import com.example.pfe.Service.EmailService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/arsii/mail")
@CrossOrigin(origins = "http://localhost:4200")


public class EmailSendController {
    private EmailService emailService;


    public EmailSendController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public String sendMail(@RequestParam String[] to, String[] cc, String subject, String body) {
        return emailService.sendMail( to, cc, subject, body);
    }


}
class EmailRequest {
    private String[] to;
    private String[] cc;
    private String subject;
    private String body;

    // Getters and setters
    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body=body;
}}
