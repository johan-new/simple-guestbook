package net.roburterra.guestbook.service;

import net.roburterra.guestbook.dao.GuestbookMessageRepository;
import net.roburterra.guestbook.domain.GuestbookMessage;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MessageService {

    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    GuestbookMessageRepository guestbookMessageRepository;

    public void newMessage(String name, String message) {
        guestbookMessageRepository.save(new GuestbookMessage(name, message));
    }

    public List<String> getMessages(){
        try {
            return DataMapper.map(guestbookMessageRepository.findAll());
        } catch (Exception e) {
            log.error("Data mapping failure");
            return new ArrayList<>();
        }
    }


    /**
     * Re-maps the data to not break compatibility with frontend
     *
     */
    static class DataMapper {

        /**
         * @param messages the data from the DynamoDB
         * @return the same data remapped
         */
        static List<String> map(List<GuestbookMessage> messages) {
            List<String> mappedData = new ArrayList<>();
            for (GuestbookMessage msg:messages) {
                Map<String,String> values = new HashMap();
                values.put("name",msg.getName());
                values.put("message", msg.getMessage());
                values.put("response", msg.getResponse());
                values.put("timestamp", msg.getTimestamp());
                mappedData.add(new JSONObject(values).toString());
            }
            return mappedData;
        }
    }
}
