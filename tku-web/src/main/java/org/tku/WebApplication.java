package org.tku;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.tku.database.repository.UserRepository;

@SpringBootApplication
@Log4j2
@Component
@EnableJpaRepositories
public class WebApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(WebApplication.class);
        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        userRepository.findById("kenny").ifPresent(user -> log.debug(user.toString()));
        userRepository.findById("john").ifPresent(user -> log.debug(user.toString()));
        userRepository.findAll().forEach(user -> log.debug(user.toString()));
        log.debug("Web Application is started! http://127.0.0.1:{}{}",
                applicationContext.getEnvironment().getProperty("server.port"),
                applicationContext.getEnvironment().getProperty("server.servlet.context-path"));
    }
}
