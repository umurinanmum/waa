package edu.mum.waa.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "file")
@Getter
@Setter
public class FileStorageProperties {

    private String uploadDir;

}
