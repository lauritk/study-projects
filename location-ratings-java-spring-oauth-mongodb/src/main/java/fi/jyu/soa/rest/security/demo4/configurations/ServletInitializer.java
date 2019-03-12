package fi.jyu.soa.rest.security.demo4.configurations;
import fi.jyu.soa.rest.security.demo4.Demo4Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

// Needed for Google App Engine
// https://stackoverflow.com/questions/49619468/spring-boot-security-does-not-work-on-google-app-engine
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Demo4Application.class);
    }

}