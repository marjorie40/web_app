package fr.pompey.cda24060.webappdemo.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="fr.pompey.cda24060.webappdemo") //pour mapper le namespace
@Data
public class CustomProperty {

    private String apiURL;
}
