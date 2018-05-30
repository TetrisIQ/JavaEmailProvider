package de.tetrisiq;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public abstract class AbstractEmailProvider implements EmailProvider {

    Properties mailServerProperties;
    Session getMailSession;
    MimeMessage generateMailMessage;
    String sendMail;
    String password;

    AbstractEmailProvider() {
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
