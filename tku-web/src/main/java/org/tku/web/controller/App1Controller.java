package org.tku.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class App1Controller {

    @GetMapping(value = "/web/app1")
    public String app1() {
        return "app1";
    }
}
