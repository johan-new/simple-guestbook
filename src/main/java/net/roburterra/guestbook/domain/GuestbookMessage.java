package net.roburterra.guestbook.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class GuestbookMessage {

    private String name,email,message,response;

    private Date timestamp;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public GuestbookMessage() {
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public GuestbookMessage(String name, String email, String message) {
        this.timestamp = new Date();
        setName(name);
        setEmail(email);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { //simple verification
        this.email = email;
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
        return response;
    }
}
