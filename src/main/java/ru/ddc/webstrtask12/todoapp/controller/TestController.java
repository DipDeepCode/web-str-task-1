package ru.ddc.webstrtask12.todoapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public ResponseEntity<?> test1() {
        return ResponseEntity.ok().body("JR");
    }
}
