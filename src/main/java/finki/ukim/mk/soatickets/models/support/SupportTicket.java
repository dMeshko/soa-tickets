package finki.ukim.mk.soatickets.models.support;

import finki.ukim.mk.soatickets.models.BaseEntity;
import finki.ukim.mk.soatickets.models.user.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by DarkoM on 09.12.2017.
 */

@Entity
@Table(name = "support_tickets")
public class SupportTicket extends BaseEntity {
    private String content;
    private Date createdOn;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    protected SupportTicket(){ }

    public SupportTicket(String content, User user) {
        this.content = content;
        this.user = user;
        this.createdOn = new Date();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
