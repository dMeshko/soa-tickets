package finki.ukim.mk.soatickets.models.notifications;

import finki.ukim.mk.soatickets.models.user.User;

import javax.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    /**Relationships**/
    @ManyToOne()
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne()
    @JoinColumn(name = "receiver_id")
    private User receiver;
    /**end relationships**/

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
