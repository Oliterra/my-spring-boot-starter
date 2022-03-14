package com.anecdotestarter.listener;

import com.anecdotestarter.service.AnecdoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@RequiredArgsConstructor
public class ContextRefreshedAnecdoteListener implements ApplicationListener<ContextRefreshedEvent> {

    private final AnecdoteService anecdoteService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(anecdoteService.getAnecdote());
    }
}

