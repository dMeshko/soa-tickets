package finki.ukim.mk.soatickets.models.user;

import finki.ukim.mk.soatickets.models.BaseEntity;
import finki.ukim.mk.soatickets.models.tickets.Ticket;
import finki.ukim.mk.soatickets.models.user.User;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by DarkoM on 09.11.2017.
 */
@Entity
@Table(name = "invoice")
public class Invoice extends BaseEntity {
    private Date purchasedOn;
    private Ticket ticket;

    protected Invoice(){}

    public Invoice(Ticket ticket, long amountPaid){
        this.ticket = ticket;
        purchasedOn = new Date();
    }

    public Date getPurchasedOn() {
        return purchasedOn;
    }

    public void setPurchasedOn(Date purchasedOn) {
        this.purchasedOn = purchasedOn;
    }

    public Ticket getTicket(){
        return ticket;
    }

    public void setTicket(Ticket ticket){
        this.ticket = ticket;
    }
}
