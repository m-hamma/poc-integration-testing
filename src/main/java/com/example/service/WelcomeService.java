package com.example.service;

import com.example.model.Message;
import com.example.repository.WelcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WelcomeService {

    private WelcomeRepository welcomeRepository;

    public String afficherMessage(String type, Integer id) {
        Optional<Message> messageOpt = welcomeRepository.findById(id);
        Message message = messageOpt.orElse(null);
        final StringBuilder sb = new StringBuilder();
        if (message != null) {
            sb.append(message.getWelcome());
            sb.append(", %s ");
            sb.append(message.getLibelle());
        }
        return String.format(sb.toString(), type);
    }

    @Autowired
    public void setWelcomeRepository(WelcomeRepository welcomeRepository) {
        this.welcomeRepository = welcomeRepository;
    }
}
