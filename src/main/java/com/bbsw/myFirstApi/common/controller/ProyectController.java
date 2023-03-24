package com.bbsw.myFirstApi.common.controller;

import com.bbsw.myFirstApi.common.service.IProyectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api")
public class ProyectController {

    @Autowired
    IProyectService proyectService;

    @GetMapping("/test/hello-world")
    public CompletableFuture<ResponseEntity<String>> helloWorldTest(@RequestParam String param){
        return CompletableFuture.supplyAsync(() -> new ResponseEntity<String>(proyectService.concatParam("test " ,param), HttpStatus.OK));
    }

    @GetMapping("/public/hello-world")
    public CompletableFuture<ResponseEntity<String>> helloWorldPublic(@RequestParam String param){
        return CompletableFuture.supplyAsync(() -> new ResponseEntity<String>(proyectService.concatParam("public " ,param), HttpStatus.OK));
    }

    @GetMapping("/private/hello-world")
    public CompletableFuture<ResponseEntity<String>> helloWorldPrivate(@RequestParam String param) {
        return CompletableFuture.supplyAsync(() -> new ResponseEntity<String>(proyectService.concatParam("private " ,param), HttpStatus.OK));
    }

    @GetMapping("/private/userdata")
    public CompletableFuture<ResponseEntity<String>> userData(@RequestParam String param) {
        return CompletableFuture.supplyAsync(() -> new ResponseEntity<String>(proyectService.concatParam("private " ,param), HttpStatus.OK));
    }


}
