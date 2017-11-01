package finki.ukim.mk.soatickets.business.view.models.events;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.Date;

public class CreateEventViewModel {

    private long ownerId;

    @NotEmpty
    @Size(min = 3)
    private String name;

    @NotEmpty
    @Size(min = 10)
    private String descrption;

    @NotEmpty
    @Size(min = 5)
    private String location;

    @NotEmpty
    private Date date;

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

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
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
