package org.tku.web.service;

import com.linecorp.bot.messaging.client.MessagingApiClient;
import com.linecorp.bot.messaging.model.Message;
import com.linecorp.bot.messaging.model.ReplyMessageRequest;
import com.linecorp.bot.messaging.model.TextMessage;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LineService {

    private final MessagingApiClient messagingApiClient;

    public LineService(MessagingApiClient messagingApiClient) {
        this.messagingApiClient = messagingApiClient;
    }

    public void replyMessage(String replyToken, String... texts){
        messagingApiClient.replyMessage(new ReplyMessageRequest(
                replyToken,
                List.of(Arrays.stream(texts).map(TextMessage::new).toArray(Message[]::new)), false
        ));
    }

}
