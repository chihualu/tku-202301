package org.tku.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @GetMapping("/index")
    public String index() throws JsonProcessingException {
        return "index";
    }
    @GetMapping(value = {"/login", "/"})
    public String login() throws JsonProcessingException {
        return "login";
    }
}
