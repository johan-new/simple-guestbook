package net.roburterra.guestbook.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="GuestbookMessages")
public class GuestbookMessage {

    private String name,message,response;

    private Date timestamp;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public GuestbookMessage() {
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public GuestbookMessage(String name, String message) {
        this.timestamp = new Date();
        setName(name);
        setMessage(message);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() > 25) //trim
            name = name.substring(0,24);

        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if (message.length()>300) //trim
            message = message.substring(0,499);
        this.message = message;
    }

    public String getResponse() {
        if (response == null) {
            return "";
        }
        return response;
    }
}
