package finki.ukim.mk.soatickets.business.view.models.messages;

import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;

import java.util.Date;

public class MessageViewModel {
    private long id;
    private UserViewModel sender;
    private UserViewModel reciever;
    private String subject;
    private String message;
    private Date date;

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

    public UserViewModel getReciever() {
        return reciever;
    }

    public void setReciever(UserViewModel reciever) {
        this.reciever = reciever;
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

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
