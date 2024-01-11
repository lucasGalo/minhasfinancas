package com.galo.minhasfinancas.config;

import com.galo.minhasfinancas.core.services.dbservice.DBServiceProd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {

    private final DBServiceProd dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Autowired
    public ProdConfig(DBServiceProd dbService) {
        this.dbService = dbService;
    }

    @Bean
    public boolean instantiateDatabase() {

        if (!"create".equals(strategy) && !"create-drop".equals(strategy)) {
            return false;
        }

        dbService.instantiateTestDatabase();

        return true;
    }
}
