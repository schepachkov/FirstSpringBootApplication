package app.controllers;

import app.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import app.util.Helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class UploadController {

    @GetMapping("/upload")
    public String getUploadForm(Model model){
        return "upload/user-form";
    }

    @PostMapping("/upload")
    public String upload(Model model, @RequestParam("file") MultipartFile file){
        model.addAttribute("ref", "/upload");
        String textRef = "try again";
        String textInfo = "Didn't found users in the file";
        try {
            File tmpFile = new File("src/main/resources/files/tmp.txt");
            Files.write(Paths.get("src/main/resources/files/tmp.txt"), file.getBytes());
            List<User> userList = Helper.readUserFromTheFile(tmpFile, true);
            if (!userList.isEmpty()){
                userList.forEach(Helper::writeUser);
                textInfo = "Congratulations! Was added " + userList.size() + " users.";
                textRef = "to upload form";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("mainText", textInfo);
        model.addAttribute("textRef", textRef);
        return "page-info";
    }
}
