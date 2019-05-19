package edu.mum.waa.controller;


import edu.mum.waa.dto.RoleEnum;
import edu.mum.waa.service.interfaces.BatchService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileController {

    private final BatchService batchService;

    @Autowired
    public FileController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping("/file/upload")
    @Secured(RoleEnum.DATA_IMPORT)
    public String uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        byte[] bytes = file.getBytes();
        if (file.getOriginalFilename().equals("attendance.csv")) {
            Path path = Paths.get("./attendance.csv");
            Files.write(path, bytes);
            batchService.startBarcode();
        } else {
            Path path = Paths.get("./manual.csv");
            Files.write(path, bytes);
            batchService.startManuel();
        }
        return "ok";
    }


}
