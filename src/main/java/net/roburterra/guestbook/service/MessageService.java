package net.roburterra.guestbook.service;

import net.roburterra.guestbook.dao.GuestbookMessageRepository;
import net.roburterra.guestbook.domain.GuestbookMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;



@org.springframework.stereotype.Service
public class MessageService {

    @Autowired
    GuestbookMessageRepository guestbookMessageRepository;

    public void newMessage(String name, String message) {
        guestbookMessageRepository.save(new GuestbookMessage(name, message));
    }

    public List<GuestbookMessage> getMessages(){
        return guestbookMessageRepository.findAll();
    }
}
