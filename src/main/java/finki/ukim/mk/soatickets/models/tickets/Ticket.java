package finki.ukim.mk.soatickets.models.tickets;

import finki.ukim.mk.soatickets.models.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

import finki.ukim.mk.soatickets.models.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity {
    private long eventId;

    public Ticket() { }

    public Ticket(long eventId) {
        this.eventId = eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getEventId() {
        return this.eventId;
    }
}
