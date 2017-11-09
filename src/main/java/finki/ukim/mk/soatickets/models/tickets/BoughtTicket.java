package finki.ukim.mk.soatickets.models.tickets;

import finki.ukim.mk.soatickets.models.BaseEntity;
import finki.ukim.mk.soatickets.models.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bought_tickets")
public class BoughtTicket extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    private Date purchasedOn;

    protected BoughtTicket() {
        purchasedOn = new Date();
    }

    public BoughtTicket(User user, Ticket ticket) {
        this.user = user;
        this.ticket = ticket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
