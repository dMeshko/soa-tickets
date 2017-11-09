package finki.ukim.mk.soatickets.business.view.models.tickets;

import finki.ukim.mk.soatickets.business.view.models.events.EventViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;

public class BoughtTicketViewModel {
    private long id;
    private EventViewModel event;
    private UserViewModel owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EventViewModel getEvent() {
        return event;
    }

    public void setEvent(EventViewModel event) {
        this.event = event;
    }

    public UserViewModel getOwner() {
        return owner;
    }

    public void setOwner(UserViewModel owner) {
        this.owner = owner;
    }
}
