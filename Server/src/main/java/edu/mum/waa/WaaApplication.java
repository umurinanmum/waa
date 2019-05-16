package edu.mum.waa;

import edu.mum.waa.dto.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class WaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaaApplication.class, args);
    }

}
