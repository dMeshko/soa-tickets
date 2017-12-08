package finki.ukim.mk.soatickets.models.user;

import finki.ukim.mk.soatickets.models.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by DarkoM on 08.12.2017.
 */

@Entity
@Table(name = "notifications")
public class Notification extends BaseEntity {
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_from_id", nullable = false)
    private User userFrom;

    @ManyToOne
    @JoinColumn(name = "user_to_id", nullable = false)
    private User userTo;

    private Date createdAt;

    protected Notification() { }

    public Notification(String content, User userFrom, User userTo) {
        this.content = content;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.createdAt = new Date();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
