package finki.ukim.mk.soatickets.models.tickets;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bought_tickets")
public class BoughtTicket extends Ticket {
    private long ownerId;

    public BoughtTicket() {
        super();
    }

    public BoughtTicket(long id, long eventId, long ownerId) {
        super(eventId);
        this.ownerId = ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public long getOwnerId() {
        return ownerId;
    }
}
