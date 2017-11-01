package finki.ukim.mk.soatickets.business.services;

import finki.ukim.mk.soatickets.business.view.models.events.CreateEventViewModel;
import finki.ukim.mk.soatickets.business.view.models.events.UpdateEventViewModel;
import finki.ukim.mk.soatickets.business.view.models.events.EventViewModel;

import java.util.Date;
import java.util.List;

public interface IEventService {
    List<EventViewModel> getAll();
    EventViewModel getById(Long eventId) throws Exception;
    Long register(CreateEventViewModel event);
    Long update(UpdateEventViewModel event);
    Long delete(Long eventId) throws Exception;
    EventViewModel findByName(String name) throws Exception;
    EventViewModel findByDate(Date date) throws Exception;
    EventViewModel findByLocation(String location) throws Exception;
}
