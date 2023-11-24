package org.tku;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.log4j.Log4j2;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.tku.database.repository.UserRepository;

@SpringBootApplication
@Log4j2
@Component
@EnableJpaRepositories
@EnableEncryptableProperties
@EnableScheduling
public class WebApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(WebApplication.class);
        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        StringEncryptor stringEncryptor = applicationContext.getBean(StringEncryptor.class);
        log.info(stringEncryptor.encrypt("password"));
        log.info(applicationContext.getEnvironment().getProperty("spring.datasource.password"));
//        log.info(applicationContext.getBean(PasswordEncoder.class).encode("123456"));
//        userRepository.findById("kenny").ifPresent(user -> log.debug(user.toString()));
//        userRepository.findById("john").ifPresent(user -> log.debug(user.toString()));
//        userRepository.findAll().forEach(user -> log.debug(user.toString()));

        log.debug("Web Application is started! http://127.0.0.1:{}{}",
                applicationContext.getEnvironment().getProperty("server.port"),
                applicationContext.getEnvironment().getProperty("server.servlet.context-path"));
    }
}
