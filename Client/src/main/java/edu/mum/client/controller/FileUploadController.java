package edu.mum.client.controller;

import edu.mum.client.helper.Constants;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {


    @GetMapping("/show")
    public String showForm() {

        return "file-upload";
    }

    @PostMapping(value = "/uploadFile")
    public String submit(@RequestParam("file") MultipartFile file) throws IOException {

        byte[] bytes = file.getBytes();
        Path path = Paths.get(Constants.UPLOAD + file.getOriginalFilename());
        Files.write(path, bytes);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file",new ByteArrayResource(bytes));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        String serverUrl = "http://localhost:8081/file/upload/";


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, requestEntity, String.class);
        //ResponseEntity<String> responseEntity = restTemplate.exchange(serverUrl, HttpMethod.POST, requestEntity, String.class);
        return "file-upload";
    }

}
