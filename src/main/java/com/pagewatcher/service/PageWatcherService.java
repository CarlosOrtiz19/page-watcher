package com.pagewatcher.service;

import com.pagewatcher.entity.AccountInformation;
import com.pagewatcher.entity.InfoDocument;

import java.io.IOException;

public interface PageWatcherService {

    void checkChanges();

    void saveFirstCheck(AccountInformation accountInformation) throws IOException;

    boolean IsDifferentInfo(InfoDocument infoDocumentBd, InfoDocument infoDocumentCurrent);

}
