package org.tku.ap.job;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class EchoJob {

    @Scheduled(cron = "0 0/5 * * * *")
    public void echo() {
       log.info(System.currentTimeMillis());
    }
}
