package com.anecdotestarter.postprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

public class ProfileDetectorEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        if (environment.getActiveProfiles().length == 0) environment.setActiveProfiles("Хиханьки-хаханьки");
        else environment.setActiveProfiles("Кончились хиханьки-хаханьки");
    }
}
