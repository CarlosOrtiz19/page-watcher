package com.pagewatcher.controller;

import com.pagewatcher.service.WatchPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/watch")
public class WatchPageController {
    @Autowired
    WatchPageService watchPageService;

    @GetMapping("/run")
    public ResponseEntity<String> getCandidatureById(@RequestParam String url,@RequestParam String session,
                                                     @RequestParam  String cookie) throws Exception {
            String response = watchPageService.runScript(url,session,cookie);
            return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
