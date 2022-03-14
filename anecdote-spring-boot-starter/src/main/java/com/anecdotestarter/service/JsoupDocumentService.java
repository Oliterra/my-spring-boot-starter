package com.anecdotestarter.service;

import org.jsoup.nodes.Document;

public interface JsoupDocumentService {

    Document getDocument(String url);
}
