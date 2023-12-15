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
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
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
//        log.info(applicationContext.getBean(StringEncryptor.class).decrypt("urtjMImAhzPhQuiyPNr8V3m84EHdxkajxGhTW+q0SEUwA4mVI/7yu5u7GTnbCU3W"));


        Document document = Jsoup.connect("https://www.taiwanlottery.com.tw/Lotto/BINGOBINGO/drawing.aspx").sslSocketFactory(WebApplication.socketFactory()).get();
//        log.info(document.html());
        Elements elements = document.getAllElements().select("td.thB table.tableFull tbody tr");
        for(Element element : elements.stream().toList()) {
            if(!element.select("td.tdA_3").isEmpty() || !element.select("td.tdA_4").isEmpty()) {
                StringBuffer sb = new StringBuffer();
                List<Element> list = element.select("td").stream().toList();
                if(!StringUtil.isBlank(list.get(0).text())) {
                    sb.append("期別:").append(list.get(0).text()).append(",");
                    sb.append("獎號:").append(list.get(1).text()).append(",");
                    sb.append("超級獎號:").append(list.get(2).text()).append(",");
                    sb.append("猜大小:").append(list.get(3).text()).append(",");
                    sb.append("猜單雙:").append(list.get(4).text());
                    log.info(sb.toString());
                }
            }
        }



    }
    static private SSLSocketFactory socketFactory() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};

        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            SSLSocketFactory result = sslContext.getSocketFactory();

            return result;
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException("Failed to create a SSL socket factory", e);
        }
    }
}
