package com.pagewatcher.service;

import com.pagewatcher.entity.InfoDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PageWatcherServiceImplTest {

    @Autowired
    PageWatcherService pageWatcherService = new PageWatcherServiceImpl();


    @Test
    void notification() throws Exception {
      
    }

    @Test
    void checkChanges() {
    }

    @Test
    void saveFirstCheck() {
    }

    @Test
    void compareInfoDocument() {

        InfoDocument infoDoc1 = new InfoDocument();
        infoDoc1.setTagDiv(1);

        InfoDocument infoDoc2 = new InfoDocument();
        infoDoc2.setTagDiv(2);

        assertTrue(pageWatcherService.IsDifferentInfo(infoDoc1, infoDoc2));


    }
}