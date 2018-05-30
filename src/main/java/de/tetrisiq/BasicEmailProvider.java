package de.tetrisiq;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

public class BasicEmailProvider extends AbstractEmailProvider implements EmailProvider {

    public BasicEmailProvider() {
    }

    /**
     * Utility method to send simple email
     */
    public void sendEmail(String toEmail, String subject, String body) {
        try {
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            generateMailMessage.setSubject(subject);
            generateMailMessage.setContent(body, "text/text");
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
}