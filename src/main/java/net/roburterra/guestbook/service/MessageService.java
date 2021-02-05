package net.roburterra.guestbook.service;

import net.roburterra.guestbook.dao.GuestbookMessageRepository;
import net.roburterra.guestbook.domain.GuestbookMessage;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@org.springframework.stereotype.Service
public class MessageService {

    @Autowired
    GuestbookMessageRepository guestbookMessageRepository;

    public void newMessage(String name, String message) {
        guestbookMessageRepository.save(new GuestbookMessage(name, message));
    }

    public List<GuestbookMessage> getMessages(){
        try {
            return DataMapper.map(guestbookMessageRepository.findAll());
        }
    }

    static class DataMapper {
        static String map(List<GuestbookMessage> messages) {
            StringBuilder dataMapped = new StringBuilder("[");

            for (GuestbookMessage msg:messages) {
                Map<String,String> values = new HashMap();
                values.put("name",msg.getName());
                values.put("message", msg.getMessage());
                values.put("response", msg.getResponse());
                values.put("timestamp", msg.getTimestamp());
                dataMapped.append(new JSONObject(values).toString() + ",");
            }
            dataMapped.substring(0,dataMapped.length()-1);
            dataMapped.append("]");

            return dataMapped.toString();
        }
    }
}
