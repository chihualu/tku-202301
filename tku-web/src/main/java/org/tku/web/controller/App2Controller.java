package org.tku.web.controller;

import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

@Controller
public class App2Controller {

    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping(value = "/web/app2")
    public String app1() {
        return "app2";
    }


    @GetMapping("/api/msg")
    public ResponseEntity tcp(@RequestParam(required = false) String msg) throws IOException {
        Socket socket = new Socket("127.0.0.1", 10001);
        OutputStream os = socket.getOutputStream();
        os.write(StringUtils.defaultString(msg, "Hello world!").getBytes());
        os.flush();
        os.close();
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/api/jms")
    public ResponseEntity jms(@RequestParam(required = false) String msg) throws IOException, JMSException {
        TextMessage message = (TextMessage) jmsTemplate.sendAndReceive("destQueue", session -> session.createTextMessage(StringUtils.defaultString(msg, "Hello World!!")));
        return ResponseEntity.ok(message.getText());
    }
}
