package de.tetrisiq;


public interface EmailProvider {

    public void sendEmail(String toEmail, String subject, String body);
    public void setupNoReplyMailAdress();
    public void configServer();
    }
