package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
import java.util.List;
/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private MessageService messageService;
    
    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account) {
        return accountService.registerAccount(account);
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account){
        return accountService.login(account);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> newMessage(@RequestBody Message message) {
        return messageService.newMessage(message);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessage() {
        return messageService.getAllMessage();
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId) {
        return messageService.getMessageById(messageId);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> delMessageById(@PathVariable Integer messageId) {
        return messageService.deleteMessage(messageId);
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessageById(@PathVariable Integer messageId, @RequestBody Message message) {
        return messageService.updateMessage(messageId, message);
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByUserId(@PathVariable Integer accountId){
        return messageService.getAllMessagesByUserId(accountId);
    }
}
