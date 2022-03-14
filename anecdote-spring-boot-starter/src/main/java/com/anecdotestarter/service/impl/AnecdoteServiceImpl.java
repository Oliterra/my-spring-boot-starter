package com.anecdotestarter.service.impl;

import com.anecdotestarter.enm.ConsoleColors;
import com.anecdotestarter.entity.Anecdote;
import com.anecdotestarter.service.AnecdoteService;
import com.anecdotestarter.service.JsoupDocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Log4j2
@Service
@RequiredArgsConstructor
public class AnecdoteServiceImpl implements AnecdoteService {

    private static final String URL = "https://anekdoty.ru/pro-programmistov";
    private final JsoupDocumentService documentService;
    private static final Random random = new Random();

    public String getAnecdote() {
        Document document = null;
        try {
            int numberOfPagesWithAnecdotes = getNumberOfPagesWithAnecdotes(URL);
            String urlWithRandomPage = formUrlWithRandomPage(URL, numberOfPagesWithAnecdotes);
            document = documentService.getDocument(urlWithRandomPage);
        } catch (Exception exception) {
            return exception.getMessage();
        }
        List<Anecdote> anecdotes = formAnecdotesList(document);
        return ConsoleColors.getRandomConsoleColor() + getRandomAnecdote(anecdotes).getText() + ConsoleColors.RESET;
    }

    private int getNumberOfPagesWithAnecdotes(String url) {
        Document doc = documentService.getDocument(url);
        Elements pagination = doc.getElementsByClass("pagination-holder-link");
        return Integer.parseInt(pagination.last().text());
    }

    private String formUrlWithRandomPage(String url, int numberOfPagesWithAnecdotes) {
        int randomPage = random.nextInt(numberOfPagesWithAnecdotes);
        return url + "/page/" + randomPage;
    }

    private List<Anecdote> formAnecdotesList(Document document) {
        List<Anecdote> anecdotes = new ArrayList<>();
        Elements rawAnecdotes = document.getElementsByTag("p");
        rawAnecdotes.forEach(a -> anecdotes.add(new Anecdote(a.text().replace(" - ", "\n- "))));
        return anecdotes;
    }

    private Anecdote getRandomAnecdote(List<Anecdote> anecdotes) {
        int randomIndex = random.nextInt(anecdotes.size());
        return anecdotes.get(randomIndex);
    }
}
