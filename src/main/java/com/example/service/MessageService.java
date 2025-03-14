package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<Message> newMessage(Message message){
        if (message.getMessageText().isBlank() || message.getMessageText().length() > 255){
            return ResponseEntity.status(400).body(null);
        }

        if (accountRepository.existsByAccountId(message.getPostedBy()) == false){
            return ResponseEntity.status(400).body(null);
        }

        Message savedMessage = messageRepository.save(message);
        return ResponseEntity.status(HttpStatus.OK).body(savedMessage);
    }
}
