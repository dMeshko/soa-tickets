package finki.ukim.mk.soatickets.models.tickets;

import finki.ukim.mk.soatickets.models.BaseEntity;
import finki.ukim.mk.soatickets.models.user.User;

import javax.persistence.*;

/**
 * Created by DarkoM on 03.12.2017.
 */

@Entity
@Table(name = "invoices")
public class Invoice extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    private float amountPayed;

    @Enumerated(EnumType.ORDINAL)
    private PaymentMethod paymentMethod;

    private Long id;

    protected Invoice(){ }

    public Invoice(User user, Ticket ticket, float amountPayed, PaymentMethod paymentMethod) {
        this.user = user;
        this.ticket = ticket;
        this.amountPayed = amountPayed;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public float getAmountPayed() {
        return amountPayed;
    }

    public void setAmountPayed(float amountPayed) {
        this.amountPayed = amountPayed;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
