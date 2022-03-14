package com.anecdotestarter.initializer;

import com.anecdotestarter.enm.ConsoleColors;
import com.anecdotestarter.exeption.WithoutProfileException;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class ProfileCheckApplicationContextInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        if (applicationContext.getEnvironment().getActiveProfiles().length == 0)
            throw new WithoutProfileException(ConsoleColors.RED_BOLD + "Невозможно работать без профиля" + ConsoleColors.RESET);
    }
}
