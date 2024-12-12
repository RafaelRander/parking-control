package com.api.controle_estacionamento.config;

import jakarta.annotation.PostConstruct;

import java.util.TimeZone;

public class SpringTimeZoneConfig {
    @PostConstruct
    public void timeZoneConfig(){
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
    }
}
