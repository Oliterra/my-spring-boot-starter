package com.anecdotestarter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("mood")
public class AnecdoteProperties {

    boolean good;
}
