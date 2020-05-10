package app.service;

import app.entity.User;
import app.util.Checkings;
import app.util.Helper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserFormService {

    public String userForm(Model model){
        model.addAttribute("user", new User());
        return "add/user-form";
    }

    public String userSubmit(Model model, User user){
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
