package edu.mum.waa.controller;


import edu.mum.waa.dto.RoleEnum;
import edu.mum.waa.security.WaaSecured;
import edu.mum.waa.service.interfaces.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1/file")
@CrossOrigin
public class FileController {

    private final BatchService batchService;

    @Autowired
    public FileController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping("/upload")
    @WaaSecured(RoleEnum.DATA_IMPORT)
    public String uploadFile(@RequestParam MultipartFile file) throws Exception {
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
