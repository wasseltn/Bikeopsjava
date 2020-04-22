/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author laoui
 */
public class Mail {

    public static void sendMail(String recepient) throws MessagingException {
        System.out.println("preparing to send email");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "*");

        String myAccountEmail = "laouiniwassel93@gmail.com";
        String password = "Xbox360wassel";

        Session session = Session.getInstance(properties, new Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(myAccountEmail, password);

            }
        });
         Message message = prepareMessage(session,myAccountEmail,recepient);
            Transport.send(message);
        System.out.println("Message envoyé");
     
    }

    private static Message prepareMessage(Session session,String recepient,String myAccountEmail ) {
          try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Votre commande est confirmée");
            message.setText("Votre commande est confirmée");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
      
    }
}
