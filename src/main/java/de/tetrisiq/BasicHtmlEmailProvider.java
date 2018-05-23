package de.tetrisiq;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BasicHtmlEmailProvider implements EmailProvider {

    Properties mailServerProperties;
    Session getMailSession;
    MimeMessage generateMailMessage;
    String sendMail;
    String password;


    public BasicHtmlEmailProvider() {
    }

    /**
     * Utility method to send simple HTML email
     */
    public void sendEmail(String toEmail, String subject, String body) {
        try {
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            generateMailMessage.setSubject(subject);
            String htmlBody = genBody(body);
            generateMailMessage.setContent(htmlBody, "text/html");
            System.out.println("Mail Session has been created successfully..");
            System.out.println("\n\n 3rd ===> Get Session and Send mail");
            Transport transport = getMailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", sendMail, password);
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    /**
     * Generate a html body
     * @param body raw body
     * @return html body
     */
    private String genBody(String body) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<h1> heading from your Company </h1>");
        stringBuilder.append("<p> and other html tags</p>\n");
        stringBuilder.append("<p>").append(body).append("</p>");
        return stringBuilder.toString();
    }

    /**
     * Setup the mail Adress where the Mails came from
     */
    public void setupNoReplyMailAdress() {
        String send = ""; //requires valid gmail id/email
        BufferedReader br;
        String pw = "";
        try {
            br = new BufferedReader(new FileReader("/home/alex/FHL-Workspace/JavaEmailProvider/src/main/java/de/tetrisiq/passwort.txt"));
            send = br.readLine();
            pw = br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        password = pw;
        sendMail = send;

    }

    /**
     * Config the Smtp Server
     */
    public void configServer() {
        Properties p;
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        p = System.getProperties();
        p.put("mail.smtp.port", "587");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        System.out.println("Mail Server Properties have been setup successfully..");
        mailServerProperties = p;
    }

}
