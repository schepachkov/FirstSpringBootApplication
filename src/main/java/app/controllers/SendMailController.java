package app.controllers;

import app.entity.MyMailSender;
import app.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SendMailController {

    @Autowired
    private SendMailService sendMailService;


    @GetMapping("/send/mail")
    public String openForm(Model model) {
        return sendMailService.openForm(model);
    }

    @PostMapping("/send/mail")
    public String sendMail(Model model, @ModelAttribute MyMailSender sender) {
        return sendMailService.sendMail(model, sender);
    }
}
