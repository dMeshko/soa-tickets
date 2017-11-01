package finki.ukim.mk.soatickets.business.services.implementation;

import finki.ukim.mk.soatickets.business.services.IEventService;
import finki.ukim.mk.soatickets.business.view.models.events.CreateEventViewModel;
import finki.ukim.mk.soatickets.business.view.models.events.EventViewModel;
import finki.ukim.mk.soatickets.business.view.models.events.UpdateEventViewModel;

import java.util.Date;
import java.util.List;

public class EventService implements IEventService {

    @Override
    public List<EventViewModel> getAll() {
        return null;
    }

    @Override
    public EventViewModel getById(Long eventId) throws Exception {
        return null;
    }

    @Override
    public Long register(CreateEventViewModel event) {
        return null;
    }

    @Override
    public Long update(UpdateEventViewModel event) {
        return null;
    }

    @Override
    public Long delete(Long eventId) throws Exception {
        return null;
    }

    @Override
    public EventViewModel findByName(String name) throws Exception {
        return null;
    }

    @Override
    public EventViewModel findByDate(Date date) throws Exception {
        return null;
    }

    @Override
    public EventViewModel findByLocation(String location) throws Exception {
        return null;
    }
}
