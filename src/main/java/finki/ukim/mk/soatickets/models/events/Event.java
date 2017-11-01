package finki.ukim.mk.soatickets.models.events;

import finki.ukim.mk.soatickets.models.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event extends BaseEntity {
    private long ownerId;
    private String name;
    private String description;
    private String location;
    private Date date;

    public Event() {}

    public Event(long ownerId, String name, String description, String location, Date date) {
        this.ownerId = ownerId;
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
    }

    public long getOwnerId() { return ownerId; }

    public void setOwnerId() { this.ownerId = ownerId; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
