package app.controllers;

import app.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @GetMapping("/upload")
    public String getUploadForm(Model model){
        return "upload/user-form";
    }

    @PostMapping("/upload")
    public String upload(Model model, @RequestParam("file") MultipartFile file){
        return uploadService.upload(model, file);
    }
}
