package edu.mum.client.controller;

import edu.mum.client.helper.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {


    @GetMapping("/show")
    public String showForm(){

        return "file-upload";
    }

    @PostMapping(value = "/uploadFile")
    public String submit(@RequestParam("file") MultipartFile file) throws IOException {

        byte[] bytes = file.getBytes();
        Path path = Paths.get(Constants.UPLOAD + file.getOriginalFilename());
        Files.write(path, bytes);


        return "file-upload";
    }

}
