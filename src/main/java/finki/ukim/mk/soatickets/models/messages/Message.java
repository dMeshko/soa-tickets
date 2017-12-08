package finki.ukim.mk.soatickets.models.messages;

import finki.ukim.mk.soatickets.models.BaseEntity;
import finki.ukim.mk.soatickets.models.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Message extends BaseEntity {
    @ManyToOne
    @JoinColumn(name="sender_id", nullable = false)
    private User sender;
    @ManyToOne
    @JoinColumn(name = "reciever_id", nullable = false)
    private User reciever;
    private String subject;
    private String message;
    private Date date;

    public Message() { this.date = new Date(); }

    public Message(User sender, User reciever, String subject, String message) {
        this.sender = sender;
        this.reciever = reciever;
        this.subject = subject;
        this.message = message;
        this.date = new Date();
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReciever() {
        return reciever;
    }

    public void setReciever(User reciever) {
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
