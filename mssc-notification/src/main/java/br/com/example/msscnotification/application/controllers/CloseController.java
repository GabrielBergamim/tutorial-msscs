package br.com.example.msscnotification.application.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/close")
public class CloseController {

    @GetMapping
    public String test() {
        return "close route";
    }
}
