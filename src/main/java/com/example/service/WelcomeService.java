package com.example.service;

import com.example.model.Message;
import com.example.repository.WelcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WelcomeService {

    private WelcomeRepository welcomeRepository;

    public String afficherMessage(String type,Integer id) {
        Optional messageOpt = welcomeRepository.findById(id);
        Message message =(Message)messageOpt.get();
        return !messageOpt.isEmpty()?String.format(message.getLibelle(),type):"";
    }
    @Autowired
    public void setWelcomeRepository(WelcomeRepository welcomeRepository) {
        this.welcomeRepository = welcomeRepository;
    }
}
