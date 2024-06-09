package com.example.pfe.Service;

public interface EmailService  {
    String sendMail (String[] to,String [] cc,String subject ,String body);
    void sendPasswordResetToken(String email, String token);
}