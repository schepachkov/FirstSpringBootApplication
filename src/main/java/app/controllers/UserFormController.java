package app.controllers;

import app.util.Checkings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import app.entity.User;
import app.util.Helper;

@Controller
public class UserFormController {

    @GetMapping("/user")
    public String userForm(Model model){
        model.addAttribute("user", new User());
        return "add/user-form";
    }

    @PostMapping("/user")
    public String userSubmit(Model model, @ModelAttribute User user){
        model.addAttribute("ref", "/user");
        if (Checkings.checkUser(user)){
            Helper.writeUser(user);
            return "add/result";
        }
        model.addAttribute("mainText", "Check your data and try again.");
        model.addAttribute("textRef", "try again");
        return "page-info";
    }
}
