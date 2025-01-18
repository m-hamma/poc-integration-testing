package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class WebAppMain implements CommandLineRunner {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            SpringApplication.run(WebAppMain.class);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application Lancée");
    }
}