import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport; 
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {

    public static void sendMail(String toEmail,boolean fileAvailable,boolean sendOTP,String otp) {
        /*parameters--
        toEmail--email address of the receiver
        fileAvaialable - true if we have to send a attachment else false
        sendOTP - true if we have to send a otp
        otp--otp to be sent if sendOTP is false then otp doesn't matter
        */

        // Recipient's email ID needs to be mentioned.
        String to = toEmail;

        // Sender's email ID needs to be mentioned
        String from = "suchithkumar2910@gmail.com";

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

                return new PasswordAuthentication("suchithkumar2910@gmail.com", "qqzmkygbukesksqp");

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


            //send otp for verification
            if(sendOTP){
                //subject for otp
                message.setSubject("Your verification OTP is "+otp);
            }
            // Set Subject: header field if there is no otp
            else
                message.setSubject("your account details are : ");

            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = new MimeBodyPart();

            MimeBodyPart textPart = new MimeBodyPart();
            if(fileAvailable){
                try {

                    File f =new File("CustomerDetails.txt");

                    attachmentPart.attachFile(f);
                    textPart.setText("This is your details");
                    multipart.addBodyPart(textPart);
                    multipart.addBodyPart(attachmentPart);

                } catch (IOException e) {

                e.printStackTrace();

            }
            message.setContent(multipart);
        }
        else{
            message.setContent(multipart);
        }

            System.out.println("sending mail...");
            // Send message
            Transport.send(message);
            System.out.println("Mail sent successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
    public static void main(String args[])
    {
        sendMail("suchithkumargm@gmail.com",false,true,"a;lkf");
    }
}