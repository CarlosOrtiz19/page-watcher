package com.pagewatcher.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WatchPageService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailService emailService;

    public String runScript(String url, String session, String cookie2, String email) throws Exception {
        Document previous = startWatchingWithCookiesDiana(url, session, cookie2);


        Document current = startWatchingWithCookiesDiana(url, session, cookie2);

        String response = "No change at the moment";

        if (previous.getElementsByTag("ul").size() != current.getElementsByTag("ul").size() ||
                previous.getElementsByTag("li").size() != current.getElementsByTag("li").size() ||
                previous.getElementsByTag("a").size() != current.getElementsByTag("a").size() ||
                previous.getElementsByTag("div").size() != current.getElementsByTag("div").size() ||
                previous.getElementsByTag("span").size() != current.getElementsByTag("span").size() ||
                !previous.getElementsByClass("error-msg").text().equals(current.getElementsByClass("error-msg").text())
        ) {
            response = "The page has changed an email was sent to your email";
            //emailService.sendEmail(email);
            //emailService.sendEmail("crisgnef@yahoo.es");
        }
        //return current.html();
        return response;
    }

    private Document startWatchingWithCookiesDiana(String url, String session, String cookie2) throws IOException {
        return Jsoup.connect(url)
                .cookie("session_id", session)
                .cookie("brighttrac_NDEB_NA", cookie2)
                .get();
    }

    private Document scripTest(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    public void runTest(String url) throws Exception {
        Document previous = scripTest(url);

        while (true) {
            Document current = scripTest(url);

            if (previous.getElementsByTag("div").size() > current.getElementsByTag("div").size() ||
                    previous.getElementsByTag("div").size() < current.getElementsByTag("div").size() ||
                    previous.getElementsByTag("span").size() > current.getElementsByTag("span").size() ||
                    previous.getElementsByTag("span").size() < current.getElementsByTag("span").size() ||
                    previous.body().equals(current.body())
            ) {
                System.out.println(" quelaue chose change  ");
            }
            previous = current;
            Thread.sleep(5000);
            System.out.println("divs");
            System.out.println(previous.body().equals(current.body()));          //counter += 5;
        }

    }

}
