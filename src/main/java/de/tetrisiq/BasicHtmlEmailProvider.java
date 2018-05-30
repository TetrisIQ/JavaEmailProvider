package de.tetrisiq;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

public class BasicHtmlEmailProvider extends AbstractEmailProvider implements EmailProvider {

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
}

