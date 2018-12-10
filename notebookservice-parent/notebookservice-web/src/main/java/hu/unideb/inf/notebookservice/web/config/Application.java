package hu.unideb.inf.notebookservice.web.config;

import hu.unideb.inf.notebookservice.service.config.ServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ServiceConfiguration.class, SecurityConfiguration.class, CustomCORSConfiguration.class})
public class Application extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
