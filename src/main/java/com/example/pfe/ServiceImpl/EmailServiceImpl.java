package com.example.pfe.ServiceImpl;


import com.example.pfe.Service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public String sendMail( String[] to, String[] cc, String subject, String body) {

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(to);
            if (cc != null) {
                mimeMessageHelper.setCc(cc); // Set CC if not null
            }
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);

            // Check if the file array is null before trying to access its length


            javaMailSender.send(mimeMessage);

            return "Mail sent successfully!";

        } catch (Exception e) {
            throw new RuntimeException("Failed to send mail", e);
  }
}
    @Override
    public void sendPasswordResetToken(String email, String token) {
        String url = "http://localhost:4200/reset-password?token=" + token; // Adjust URL as needed

        SimpleMailMessage emailMessage = new SimpleMailMessage();
        emailMessage.setTo(email);
        emailMessage.setSubject("Password Reset Request");
        emailMessage.setText("To reset your password, click the link below:\n" + url);
        javaMailSender.send(emailMessage);
    }

}
