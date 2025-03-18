package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ResponseEntity<List<Message>> getAllMessage(){
        List<Message> allMessages = messageRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allMessages);
    }

    public ResponseEntity<Message> getMessageById(Integer messageId){
        Message message = messageRepository.findByMessageId(messageId);

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    public ResponseEntity<Integer> deleteMessage(Integer messageId){
        if (messageRepository.existsById(messageId)){
            messageRepository.deleteById(messageId);
            return ResponseEntity.status(HttpStatus.OK).body(1);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    public ResponseEntity<Integer> updateMessage(Integer messageId, Message messageImport){
        if (messageRepository.existsById(messageId) && messageImport.getMessageText().isBlank() == false && 
        messageImport.getMessageText().length() <= 255){
            Message message = messageRepository.findByMessageId(messageId);
            message.setMessageText(messageImport.getMessageText());
            messageRepository.save(message);
            return ResponseEntity.status(HttpStatus.OK).body(1);
                
        }
        return ResponseEntity.status(400).body(null);
    }

    public ResponseEntity<List<Message>> getAllMessagesByUserId(Integer accountId){
        List<Message> allMessages = messageRepository.findByPostedBy(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(allMessages);
    }
}
