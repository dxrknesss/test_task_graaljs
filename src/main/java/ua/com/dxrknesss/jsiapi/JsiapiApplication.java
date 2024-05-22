package ua.com.dxrknesss.jsiapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class JsiapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsiapiApplication.class, args);
    }

}
