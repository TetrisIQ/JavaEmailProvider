package de.tetrisiq;

import javax.mail.MessagingException;

public class main {



    /**
     * To send mails suggsesfull you have to create a password.txt file
     * you can find a sample at password_sample.txt
     */
    public static void main(String[] args) throws MessagingException {
        EmailProvider mail = new BasicEmailProvider();
        mail.configServer();
        mail.setupNoReplyMailAdress();
        String toEmail = "toMail@mail.com";
        String subject = "subject";
        String body = "Body";
        mail.sendEmail(toEmail, subject, body);

    }








}
