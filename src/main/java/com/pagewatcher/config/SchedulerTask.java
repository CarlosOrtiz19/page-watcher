package com.pagewatcher.config;

import com.pagewatcher.service.PageWatcherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Slf4j
@Component
public class SchedulerTask {

    @Autowired
    PageWatcherService pageWatcherService;

    @Scheduled(fixedRate = 60000)
    public void checkForTask() {
        log.info("start scheduler at: {}", Timestamp.from(Instant.now()));

        pageWatcherService.checkChanges();

        log.info("end of sheduler's task");
    }

}
