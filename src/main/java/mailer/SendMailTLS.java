package mailer;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by Denis Stoianov on 09/09/2014, 5:02 PM
 * E-mail: denis@revimedia.com
 */

public class SendMailTLS {

    private String username = "test.techinsight@gmail.com";
    private String password = "22222299";

    public SendMailTLS(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void sendEmail(String to, String subject, String bodyText, String[] att) {

        // Step1
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        System.out.println("Mail Server Properties have been setup successfully..");

        // Step2
        System.out.println("\n 2nd ===> get Mail Session..");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        System.out.println("Mail Session has been created successfully..");

        try {
            System.out.println("\n 3nd ===> start Building the message..");

            String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH).format(new Date());

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject + " " + time);

            // Create a multipart message
            Multipart multiPart = new MimeMultipart();

            // Create the message part
            MimeBodyPart messageText = new MimeBodyPart();
            messageText.setContent(bodyText, "text/plain");
            multiPart.addBodyPart(messageText);


            for (String attachedFile : att) {
                setFileAsAttachment(multiPart, System.getProperty("user.dir") + attachedFile);
            }
            /**
             * set the message's content as the multipart obj
             */
            message.setContent(multiPart);

            System.out.println("Mail Message has been built successfully..");

            System.out.println("\n 4th ===> Sending Email message..");

            Transport.send(message);

            System.out.println("Mail Message has been sent successfully..");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setFileAsAttachment(Multipart multipart, String filename) throws MessagingException {
        System.out.println("Attaching file " + filename);
        MimeBodyPart attachment = new MimeBodyPart();
        FileDataSource fds = new FileDataSource(filename);
        attachment.setDataHandler(new DataHandler(fds));
        attachment.setFileName(fds.getName());
        multipart.addBodyPart(attachment);
    }
}
