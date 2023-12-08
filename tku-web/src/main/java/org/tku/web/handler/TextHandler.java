package org.tku.web.handler;

import com.linecorp.bot.spring.boot.handler.annotation.EventMapping;
import com.linecorp.bot.spring.boot.handler.annotation.LineMessageHandler;
import com.linecorp.bot.webhook.model.MessageEvent;
import com.linecorp.bot.webhook.model.StickerMessageContent;
import com.linecorp.bot.webhook.model.TextMessageContent;
import lombok.extern.log4j.Log4j2;
import org.tku.web.service.LineService;


@LineMessageHandler
@Log4j2
public class TextHandler {

    private final LineService lineService;

    public TextHandler(LineService lineService) {
        this.lineService = lineService;
    }


    @EventMapping
    public void handleTextMessageEvent(MessageEvent event, TextMessageContent textMessageContent) {
        log.info("replyToken: " + event.replyToken());
        log.info("text: " + textMessageContent.text());
        lineService.replyMessage(event.replyToken(), textMessageContent.text());
    }

    @EventMapping
    public void handleSticksMessageEvent(MessageEvent event, StickerMessageContent stickerMessageContent) {
        log.info("replyToken: " + event.replyToken());
        log.info("StickerId: " + stickerMessageContent.stickerId());
        log.info("packageId: " + stickerMessageContent.packageId());

    }
}
