package net.roburterra.guestbook.controller;

import net.roburterra.guestbook.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebControllerImpl {

    @Autowired
    MessageService messageService;

    @PostMapping("/guestbook")
    ResponseEntity newPost(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("message") String message) {
        if (name == null || name.isBlank())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not a valid name");
        if (!validEmail(email))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not a valid email");
        if (message == null || message.isBlank())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not a valid message");


        messageService.newMessage(name,email,message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/guestbook")
    ResponseEntity getPosts(){
        return  ResponseEntity.ok(messageService.getMessages());
    }

    private boolean validEmail(String email) {
        return email.contains("@") && email.contains(".") &&
                !email.startsWith(".") && !email.endsWith(".") &&
                !email.startsWith("@") && !email.endsWith("@");
    }

}
