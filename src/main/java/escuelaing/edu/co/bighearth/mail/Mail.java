package escuelaing.edu.co.bighearth.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

public abstract class Mail {

    private static String username = "betomilol271@gmail.com";
    private static String password = "martinjhmMESSI10";
    private static boolean ans=true;


    public static boolean sendEmail(List<String> emails,List<String> mailInfo){

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            for(String email:emails){
                System.out.println(email);
                Message message = new MimeMessage(session);
                System.out.println("1");
                message.setFrom(new InternetAddress("betomilol271@gmail.com"));
                System.out.println("2");
                message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
                System.out.println("3");
                message.setSubject(mailInfo.get(0));
                System.out.println("4");
                message.setText(mailInfo.get(1));
                System.out.println("5");
                session.getTransport("smtps").send(message);

                System.out.println("Done");
            }


        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            ans=false;
        }

        return ans;
    }

}

