package app.service;

import app.entity.User;
import app.util.Helper;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.GregorianCalendar;

@Service
public class FindUserService {

    public String submitFindUser(Model model, User user, String agent){
        model.addAttribute("ref", "/find");
        user = Helper.readUserForName(user.getFirstName().trim(), user.getLastName().trim());
        if (user != null) {
            UserAgentAnalyzer agentAnalyzer = UserAgentAnalyzer.newBuilder().build();
            UserAgent userAgent = agentAnalyzer.parse(agent);
            String browsInfo = "";
            browsInfo += userAgent.getValue("AgentClass") + " ";
            browsInfo += userAgent.getValue("AgentName");

            model.addAttribute("fn", user.getFirstName());
            model.addAttribute("ln", user.getLastName());
            model.addAttribute("mn", user.getMiddleName());
            model.addAttribute("sal", user.getSalary());
            model.addAttribute("work", user.getWorkName());
            model.addAttribute("mail", user.getMail());
            model.addAttribute("age", user.getAge());
            model.addAttribute("browserInfo", browsInfo);
            model.addAttribute("time", new GregorianCalendar().getTime());

            return "find/result";
        }
        model.addAttribute("mainText", "User is not found");
        model.addAttribute("ref", "/find");
        model.addAttribute("textRef", "try again");
        return "page-info";
    }


    public String openFindFormUser(Model model){
        model.addAttribute("user", new User());
        return "find/user-form";
    }
}
