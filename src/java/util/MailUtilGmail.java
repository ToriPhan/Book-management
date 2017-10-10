package util;

import java.util.Properties;
import javax.mail.Address;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author iviettech
 */
public class MailUtilGmail {
    public static void sendEmail(String to, String subject, String body) {
        try{
        //1 - Get an email session
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.port", "465");
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        
        //2 - Create a message
        Message message = new MimeMessage(session);
        message.setSubject(subject);
        message.setContent(body, "text/html");
       
        // 3 - address the message
        Address fromAddress = new InternetAddress("conos.team@gmail.com", "JV19 - M06");
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);
        
        // 4 - send the message
        Transport transport = session.getTransport();
        transport.connect("conos.team@gmail.com", "axoninsight123");
        transport.sendMessage(message, message.getAllRecipients());
        System.out.println("Done");
        transport.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
