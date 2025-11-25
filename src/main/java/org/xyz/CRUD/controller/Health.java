package org.xyz.CRUD.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {
    @GetMapping("health")
    public String checkHealth() {
        return "running";
    }
}
