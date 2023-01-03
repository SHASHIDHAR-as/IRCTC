import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

import javax.lang.model.element.Name;
import javax.mail.*;
import javax.mail.internet.*;

public class MailAttachment {
    public static void sendConfirmation(String email,String UserName,ArrayList<String> details,String msg,String fileMsg){
        Customerfile c=new Customerfile();
        String from = "shashidhar.shivaraj715@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass 
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("shashidhar.shivaraj715@gmail.com", "iyvbmvkfascqoske");

            }

        });
        session.setDebug(false);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            // Set Subject: header field
            message.setSubject("IRCTC RAILWAY RESERVATION");



            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = new MimeBodyPart();

            MimeBodyPart textPart = new MimeBodyPart();

            try {
                
                File f =new File(UserName+".txt");
                c.createfile(UserName);
                c.writefile(details,UserName,fileMsg);
                
                attachmentPart.attachFile(f);
                
                textPart.setText(msg);
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);
                
            } catch (IOException e) {
                
                e.printStackTrace();
                
            }
            
            message.setContent(multipart);
            
            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
            c.deletefile(UserName);
        }
        catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    public static void main(String args[]){
        
        // Recipient's email ID needs to be mentioned.
        String to = "shashidhar09735@gmail.com";
        String name="shashidhaaaar";
        ArrayList<String> details=new ArrayList<String>();
        details.add(name);
        details.add("Chintamni");
        details.add("9019071753");

        // Sender's email ID needs to be mentioned
        sendConfirmation(to,name,details,"sample","\n\nYour Train tickets are booked \n\nDETAILS :\n\n");
    
    }
}