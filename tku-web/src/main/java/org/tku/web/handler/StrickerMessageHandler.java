package org.tku.web.handler;

import com.linecorp.bot.messaging.client.MessagingApiClient;
import com.linecorp.bot.messaging.model.ReplyMessageRequest;
import com.linecorp.bot.messaging.model.RichMenuRequest;
import com.linecorp.bot.messaging.model.StickerMessage;
import com.linecorp.bot.spring.boot.handler.annotation.EventMapping;
import com.linecorp.bot.spring.boot.handler.annotation.LineMessageHandler;
import com.linecorp.bot.webhook.model.MessageEvent;
import com.linecorp.bot.webhook.model.StickerMessageContent;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@LineMessageHandler
@Log4j2
public class StrickerMessageHandler {

    private final MessagingApiClient messagingApiClient;

    public StrickerMessageHandler(MessagingApiClient messagingApiClient) {
        this.messagingApiClient = messagingApiClient;
    }

    @EventMapping
    public void stickerHandler(MessageEvent event, StickerMessageContent messageContent) {
        log.info(messageContent.packageId());
        log.info(messageContent.stickerId());
        log.info(event.replyToken());
        messagingApiClient.replyMessage(new ReplyMessageRequest(event.replyToken(),
                List.of(new StickerMessage("446", "1988")), false));
    }

}
