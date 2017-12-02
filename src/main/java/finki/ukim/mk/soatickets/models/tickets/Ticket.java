package finki.ukim.mk.soatickets.models.tickets;

import finki.ukim.mk.soatickets.models.BaseEntity;

import javax.persistence.*;

import finki.ukim.mk.soatickets.models.BaseEntity;
import finki.ukim.mk.soatickets.models.events.Event;
import org.hibernate.search.annotations.Field;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Field
    private int price;

    protected Ticket() { }

    public Ticket(Event event, int price) {
        this.event = event;
        this.price = price;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
