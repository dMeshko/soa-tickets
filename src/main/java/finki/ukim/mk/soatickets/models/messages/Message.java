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
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;
    private String subject;
    private String message;
    private Date date;

    public Message() { this.date = new Date(); }

    public Message(User sender, User receiver, String subject, String message) {
        this.sender = sender;
        this.receiver = receiver;
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

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
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

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
