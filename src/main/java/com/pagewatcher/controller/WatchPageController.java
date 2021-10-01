package com.pagewatcher.controller;

import com.pagewatcher.entity.AccountInformation;
import com.pagewatcher.service.PageWatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/watch")
public class WatchPageController {
    @Autowired
    PageWatcherService pageWatcherService;

    @GetMapping("/start")
    public ResponseEntity<String> getCandidatureById(@RequestBody AccountInformation accountInformation) throws Exception {
        pageWatcherService.saveFirstCheck(accountInformation);
        return ResponseEntity.status(HttpStatus.OK).body("la página será monitoreada cada 10 minutos, " +
                "si se detecta algún cambio se enviará un mensaje al correo electrónico " +
                accountInformation.getEmail());
    }
}
