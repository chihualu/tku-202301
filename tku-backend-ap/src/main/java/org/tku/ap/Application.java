package org.tku.ap;

import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

@SpringBootApplication(scanBasePackages = "org.tku", exclude = ActiveMQAutoConfiguration.class)
@EnableScheduling
@Log4j2
@EnableJms
@Component
public class Application implements CommandLineRunner {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        Message message = jmsTemplate.sendAndReceive("destQueue", session -> session.createTextMessage("Hello world!"));

        log.info("message.getJMSMessageID = {}", message.getJMSMessageID());
        log.info("message.getJMSCorrelationID = {}", message.getJMSCorrelationID());

        log.info("Origin Receive => {}", ((TextMessage)message).getText());
        ServerSocket ss = new ServerSocket(10001);
        while(true) {
            log.info("Wait Socket {} connect", 10001);
            Socket socket = ss.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String line = br.readLine();
                if (line != null) {
                    log.info(line);
                } else {
                    break;
                }
            }
        }
    }
}
