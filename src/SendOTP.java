import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SendOTP {
    public static void sendOTP(String otp,String to){
        // Recipient's email ID needs to be mentioned.
        // String to = "projectemail295@gmail.com";

        // Sender's email ID needs to be mentioned
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
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("OTP");
            Multipart multipart = new MimeMultipart();

            MimeBodyPart textPart = new MimeBodyPart();

            textPart.setText("Your OTP is "+otp);
            multipart.addBodyPart(textPart);

            message.setContent(multipart);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    public static void main(String args[]){
        sendOTP("kljsdfg","shashidahr.shivarj715@gmail.com");
    }
}

