package com.anecdotestarter.config;

import com.anecdotestarter.advice.GetMethodInfoAdvice;
import com.anecdotestarter.annotation.GetMethodInfo;
import com.anecdotestarter.listener.ContextRefreshedAnecdoteListener;
import com.anecdotestarter.properties.AnecdoteProperties;
import com.anecdotestarter.service.AnecdoteService;
import com.anecdotestarter.service.impl.AnecdoteServiceImpl;
import com.anecdotestarter.service.impl.JsoupDocumentServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableConfigurationProperties(AnecdoteProperties.class)
public class AnecdoteConfig {

    @Bean
    @ConditionalOnProperty(name = "mood.good", havingValue = "true")
    public AnecdoteService anecdoteService() {
        return new AnecdoteServiceImpl(new JsoupDocumentServiceImpl());
    }

    @Bean
    @ConditionalOnBean(AnecdoteService.class)
    @Profile("Хиханьки-хаханьки")
    public ContextRefreshedAnecdoteListener anecdoteListener() {
        return new ContextRefreshedAnecdoteListener(anecdoteService());
    }

    @Bean
    @ConditionalOnClass(GetMethodInfo.class)
    public GetMethodInfoAdvice getMethodInfoAdvice() {
        return new GetMethodInfoAdvice(anecdoteService());
    }
}
