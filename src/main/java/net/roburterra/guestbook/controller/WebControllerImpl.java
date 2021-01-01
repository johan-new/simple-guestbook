package net.roburterra.guestbook.controller;

import net.roburterra.guestbook.domain.GuestbookMessage;
import net.roburterra.guestbook.service.IPBlocker;
import net.roburterra.guestbook.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@RestController
public class WebControllerImpl {

    @Autowired
    MessageService messageService;

    @Autowired
    IPBlocker ipBlocker;

    @CrossOrigin
    @PostMapping("/guestbook")
    ResponseEntity newPost(@RequestParam("name") String name,
                           @RequestParam("message") String message,
                           HttpServletRequest request) {
        if (name == null || name.isBlank() || message.length() < 3)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not a valid name");
        if (message == null || message.isBlank() || message.length() < 3)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not a valid message");
        if (ipBlocker.isBlocked(request.getRemoteAddr()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        messageService.newMessage(name,message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin
    @GetMapping("/guestbook")
    ResponseEntity getPosts(){
        List<GuestbookMessage> responseContent = messageService.getMessages();
        Collections.reverse(responseContent);
        return  ResponseEntity.ok(responseContent);
    }

}
