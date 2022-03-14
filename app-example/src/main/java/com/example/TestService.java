package com.example;

import com.anecdotestarter.annotation.GetMethodInfo;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TestService {

    @GetMethodInfo
    public String testMethodForChekingInfoGetting(String name) {
        Random random = new Random();
        if (random.nextBoolean()) {
            System.out.println("Hello, " + name);
            return name;
        } else throw new RuntimeException();
    }
}
