package org.tku.ap.listener;


import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MessageListenerService {

    @JmsListener(destination = "destQueue")
    public String onMessage(Message message) throws JMSException {
        log.info(message.getJMSReplyTo());
        log.info("message.getJMSMessageID = {}", message.getJMSMessageID());
        log.info("message.getJMSCorrelationID = {}", message.getJMSCorrelationID());
       log.info("Receive msg => {}", ((TextMessage)message).getText());


       return "Got it";
    }

}
