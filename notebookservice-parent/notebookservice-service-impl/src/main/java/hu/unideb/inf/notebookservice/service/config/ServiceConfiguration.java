package hu.unideb.inf.notebookservice.service.config;

import hu.unideb.inf.notebookservice.persistence.config.PersistenceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceConfiguration.class)
@ComponentScan("hu.unideb.inf.notebookservice.service")
public class ServiceConfiguration {
}
