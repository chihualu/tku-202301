package org.tku.ap.config;

import lombok.extern.log4j.Log4j2;
import org.apache.activemq.artemis.core.config.Configuration;
import org.apache.activemq.artemis.core.settings.impl.AddressSettings;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisConfigurationCustomizer;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ArtemisConfig implements ArtemisConfigurationCustomizer {
    @Override
    public void customize(Configuration configuration) {
        AddressSettings addressSettings = new AddressSettings();
        configuration.addAddressSetting("#", addressSettings);
        configuration.setPersistenceEnabled(false);
        configuration.setJMXManagementEnabled(true);
        log.info("Initialising ArtemisConfiguration");
        try {
            configuration.addAcceptorConfiguration("remote", "tcp://0.0.0.0:61616");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
