package org.tku.ap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
public class JmsConfiguration {


    @Bean
    public DefaultJmsListenerContainerFactory factory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConcurrency("0-5");
        factory.setSessionTransacted(true);
        factory.setMaxMessagesPerTask(1);
        return factory;
    }
}
