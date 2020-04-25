package app.entity;




import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MyMailSender {
    private String from;
    private String pass;
    private String to;
    private String textMessage;
    private String textSubject;

    public MyMailSender() {
    }


    public static boolean sendMessage(MyMailSender sender){
        String mail = sender.getFrom().substring(sender.getFrom().indexOf("@") + 1);
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp." + mail);
        properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender.getFrom(),  sender.getPass());
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(sender.getFrom());
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(sender.getTo()));
            message.setSubject(sender.getTextSubject());
            message.setText(sender.getTextMessage());
            Transport.send(message);
            return true;
        }catch (MessagingException e){
            e.printStackTrace();
        }
        return false;
    }





    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getTextSubject() {
        return textSubject;
    }

    public void setTextSubject(String textSubject) {
        this.textSubject = textSubject;
    }
}
