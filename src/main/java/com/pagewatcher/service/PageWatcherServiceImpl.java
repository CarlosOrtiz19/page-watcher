package com.pagewatcher.service;

import com.pagewatcher.entity.AccountInformation;
import com.pagewatcher.entity.InfoDocument;
import com.pagewatcher.repository.AccountInformationRepository;
import com.pagewatcher.repository.InfoDocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class PageWatcherServiceImpl implements PageWatcherService {

    @Autowired
    AccountInformationRepository accountInformationRepository;

    @Autowired
    InfoDocumentRepository infoDocumentRepository;

    @Autowired
    WatchPageService watchPageService;

    @Autowired
    private EmailService emailService;

    @Override
    public void checkChanges() {
        List<AccountInformation> list = accountInformationRepository.findAll();


        list.stream().forEach(accountInformation -> {
            try {
                InfoDocument updateInfoAccount = getInfoAccount(accountInformation);

                if (!IsDifferentInfo(updateInfoAccount, accountInformation.getInfoDocument())) {
                    log.info("changes detected, email send to {} : ", accountInformation.getEmail());
                    emailService.sendEmail(accountInformation.getEmail(), accountInformation.getUrl());
                }
                log.info("non changes in the page");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void saveFirstCheck(AccountInformation accountInformation) throws IOException {
        InfoDocument getInfoAccount = getInfoAccount(accountInformation);

        infoDocumentRepository.saveAndFlush(getInfoAccount);
        accountInformation.setInfoDocument(getInfoAccount);
        accountInformationRepository.saveAndFlush(accountInformation);

    }

    private InfoDocument getInfoAccount(AccountInformation accountInformation) throws IOException {
        Document infoCurrentDoc = startWatchingWithCookiesDiana(accountInformation);

        InfoDocument infoDocument = new InfoDocument();
        infoDocument.setTagA(infoCurrentDoc.getElementsByTag("a").size());
        infoDocument.setTagLi(infoCurrentDoc.getElementsByTag("li").size());
        infoDocument.setTagLi(infoCurrentDoc.getElementsByTag("ul").size());
        infoDocument.setTagDiv(infoCurrentDoc.getElementsByTag("div").size());
        infoDocument.setTagSpam(infoCurrentDoc.getElementsByTag("spam").size());
        infoDocument.setErrorMesagge(infoCurrentDoc.getElementsByClass("error-msg").text());
        infoDocument.setEmail(accountInformation.getEmail());
        return infoDocument;
    }

    private Document startWatchingWithCookiesDiana(AccountInformation accountInformation) throws IOException {
        return Jsoup.connect(accountInformation.getUrl())
                .cookie("session_id", accountInformation.getSession())
                .cookie("brighttrac_NDEB_NA", accountInformation.getCookie())
                .get();
    }

    public boolean IsDifferentInfo(InfoDocument infoDocumentBd, InfoDocument infoDocumentCurrent) {

        return infoDocumentBd.getTagA() != infoDocumentCurrent.getTagA()
                || infoDocumentBd.getTagDiv() != infoDocumentCurrent.getTagDiv()
                || infoDocumentBd.getTagLi() != infoDocumentCurrent.getTagLi()
                || infoDocumentBd.getTagSpam() != infoDocumentCurrent.getTagSpam()
                || infoDocumentBd.getTagUl() != infoDocumentCurrent.getTagUl()
                || infoDocumentBd.getErrorMesagge() != infoDocumentCurrent.getErrorMesagge();
    }

}
