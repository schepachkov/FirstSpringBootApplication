package app.controllers;

import app.entity.MyMailSender;
import app.util.Checkings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SendMailController {

    @GetMapping("/send/mail")
    public String openForm(Model model) {
        model.addAttribute("sender", new MyMailSender());
        return "mail/send";
    }

    @PostMapping("/send/mail")
    public String sendMail(Model model, @ModelAttribute MyMailSender sender) {
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
