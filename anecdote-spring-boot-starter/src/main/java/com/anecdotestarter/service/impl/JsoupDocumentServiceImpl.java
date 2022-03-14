package com.anecdotestarter.service.impl;

import com.anecdotestarter.enm.ConsoleColors;
import com.anecdotestarter.exeption.NoAnecdotesFoundException;
import com.anecdotestarter.service.JsoupDocumentService;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class JsoupDocumentServiceImpl implements JsoupDocumentService {

    private Document document;

    @Override
    public Document getDocument(String url) {
        try {
            document = Jsoup.connect(url).get();
        } catch (Exception e) {
            throw new NoAnecdotesFoundException(ConsoleColors.RED_BOLD + "Кончились хиханьки-хаханьки((" + ConsoleColors.RESET);
        }
        return document;
    }
}