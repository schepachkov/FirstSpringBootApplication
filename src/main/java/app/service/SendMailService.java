package app.service;

import app.entity.MyMailSender;
import app.util.Checkings;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class SendMailService {

    public String openForm(Model model){
        model.addAttribute("sender", new MyMailSender());
        return "mail/send";
    }


    public String sendMail (Model model, MyMailSender sender){
        if (Checkings.checkMail(sender.getFrom())
                && Checkings.checkMail(sender.getTo())
                && MyMailSender.sendMessage(sender)) {
            model.addAttribute("mainText", "Successfully!");
            model.addAttribute("text", "Message was sent");
            model.addAttribute("ref", "/user");
            model.addAttribute("textRef", "to user form");
        } else {
            model.addAttribute("mainText", "Message wasn't sent");
            model.addAttribute("text", "Please check your data and try again");
            model.addAttribute("ref", "/send/mail");
            model.addAttribute("textRef", "try again");
        }
        return "page-info";
    }
}
