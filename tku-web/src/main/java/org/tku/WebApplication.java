package org.tku;

import com.linecorp.bot.messaging.client.MessagingApiClient;
import com.linecorp.bot.messaging.model.ReplyMessageRequest;
import com.linecorp.bot.messaging.model.TextMessage;
import com.linecorp.bot.spring.boot.handler.annotation.EventMapping;
import com.linecorp.bot.spring.boot.handler.annotation.LineMessageHandler;
import com.linecorp.bot.webhook.model.MessageEvent;
import com.linecorp.bot.webhook.model.TextMessageContent;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import lombok.extern.log4j.Log4j2;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.tku.database.repository.UserRepository;

import java.util.List;

@SpringBootApplication
@Log4j2
@Component
@EnableJpaRepositories
@EnableEncryptableProperties
@EnableScheduling
@EnableJms
@LineMessageHandler
public class WebApplication implements CommandLineRunner {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private MessagingApiClient messagingApiClient;
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(WebApplication.class);
        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("Web Application is started! http://127.0.0.1:{}{}",
                applicationContext.getEnvironment().getProperty("server.port"),
                applicationContext.getEnvironment().getProperty("server.servlet.context-path"));
    }

}
