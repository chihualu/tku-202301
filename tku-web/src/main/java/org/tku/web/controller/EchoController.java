package org.tku.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EchoController {

    @GetMapping("/echo")
    public String echo(@RequestParam(name = "message", required = false) String message){
        if(StringUtils.isBlank(message)){
            message = "Hello World!";
        }
        return message;
    }

    @GetMapping("/json")
    public String json(){
        return "{\"message\":\"Hello World!\"}";
    }

    @GetMapping("/pretty")
    public String pretty(@RequestParam(name = "message") String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = new HashMap<>();
        map.put("message", message);
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
    }
    @PostMapping("/pretty")
    public String pretty2(@RequestBody String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Object object = objectMapper.readValue(json, Object.class);
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }
}
