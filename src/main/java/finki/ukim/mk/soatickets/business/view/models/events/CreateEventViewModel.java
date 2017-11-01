package finki.ukim.mk.soatickets.business.view.models.events;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class CreateEventViewModel {
    private long ownerId;

    @NotEmpty
    @Size(min = 3)
    private String name;

    @NotEmpty
    @Size(min = 10)
    private String description;

    @NotEmpty
    @Size(min = 5)
    private String location;

    @NotEmpty
    private String date;

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
