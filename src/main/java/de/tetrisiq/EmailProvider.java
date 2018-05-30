package de.tetrisiq;


public interface EmailProvider {

    void sendEmail(String toEmail, String subject, String body);
    void setupNoReplyMailAdress();
    void configServer();
    }
