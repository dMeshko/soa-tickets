package finki.ukim.mk.soatickets.business.view.models.messages;

import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;

import java.util.Date;

public class MessageViewModel {
    private long id;
    private UserViewModel sender;
    private UserViewModel receiver;
    private String subject;
    private String message;
    private String date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserViewModel getSender() {
        return sender;
    }

    public void setSender(UserViewModel sender) {
        this.sender = sender;
    }

    public UserViewModel getReceiver() {
        return receiver;
    }

    public void setReceiver(UserViewModel receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
