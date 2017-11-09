package finki.ukim.mk.soatickets.models.tickets;

import finki.ukim.mk.soatickets.models.BaseEntity;
import finki.ukim.mk.soatickets.models.user.Invoice;
import finki.ukim.mk.soatickets.models.user.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bought_tickets")
public class BoughtTicket extends BaseEntity {
    @ManyToMany
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private Ticket ticket;
    private Invoice invoice;

    protected BoughtTicket() {}

    public BoughtTicket(User user, Ticket ticket, Invoice invoice) {
        this.user = user;
        this.ticket = ticket;
        this.invoice = invoice;
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

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
