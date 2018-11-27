package hu.unideb.inf.notebookservice.web.config;

import hu.unideb.inf.notebookservice.persistence.config.PersistenceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Import({PersistenceConfiguration.class, SecurityConfiguration.class, CustomCORSConfiguration.class})
public class Application extends SpringBootServletInitializer {

    @RequestMapping("/")
    public String home() {
        return "Hello Docker World";
    }

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
