package org.tku.web.job;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
@Log4j2
public class KeepAlive {

    /**
     * 每10秒執行一次
     * 秒 分 時 日 月 周 (年) 年是可選參數，不一定要寫，其他都是必須的
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void keepAlive1() {
        log.info("Cron => " + DateFormatUtils.format(new Date(), "yyyy/MM/dd'T'HH:mm:ss"));
    }


    /**
     * 執行完後休息一秒後再執行
     */
    @Scheduled(fixedDelay = 1000L)
    public void keepAlive2() {
        log.info("fixedDelay => " + DateFormatUtils.format(new Date(), "yyyy/MM/dd'T'HH:mm:ss"));
    }


    /**
     * 每兩秒執行一次
     */
    @Scheduled(fixedRate = 2000L )
    public void keepAlive3() {
        log.info("fixedRate => " + DateFormatUtils.format(new Date(), "yyyy/MM/dd'T'HH:mm:ss"));
    }
}
