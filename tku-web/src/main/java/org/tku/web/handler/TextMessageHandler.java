package org.tku.web.handler;

import com.linecorp.bot.messaging.client.MessagingApiClient;
import com.linecorp.bot.messaging.model.ReplyMessageRequest;
import com.linecorp.bot.messaging.model.StickerMessage;
import com.linecorp.bot.messaging.model.TextMessage;
import com.linecorp.bot.spring.boot.handler.annotation.EventMapping;
import com.linecorp.bot.spring.boot.handler.annotation.LineMessageHandler;
import com.linecorp.bot.webhook.model.MessageEvent;
import com.linecorp.bot.webhook.model.TextMessageContent;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@LineMessageHandler
@Log4j2
public class TextMessageHandler {

    private final MessagingApiClient messagingApiClient;

    public TextMessageHandler(MessagingApiClient messagingApiClient) {
        this.messagingApiClient = messagingApiClient;
    }

    @EventMapping
    public void textHandler(MessageEvent event, TextMessageContent messageContent) {
        log.info(messageContent);
        log.info(event.replyToken());
        messagingApiClient.replyMessage(new ReplyMessageRequest(event.replyToken(),
                List.of(new StickerMessage("446", "1988")), false));

    }

}
