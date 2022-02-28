package com.tsypk.sniper.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tsypk on 23.02.2022 17:44
 * @project sniper
 */

@Controller
@Slf4j
public class MainController {
    @RequestMapping(value = "/")
    public String index() {
        log.info("[index] index.html page is returned");
        return "index";
    }

    @GetMapping("/users/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/users/success")
    public String getSuccessPage() {
        return "success";
    }
}
