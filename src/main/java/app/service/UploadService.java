package app.service;

import app.entity.User;
import app.util.Helper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class UploadService {

    public String upload(Model model, MultipartFile file){

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
