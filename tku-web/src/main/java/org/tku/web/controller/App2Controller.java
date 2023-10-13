package org.tku.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class App2Controller {

    @GetMapping(value = "/web/app2")
    public String app1() {
        return "app2";
    }
}
