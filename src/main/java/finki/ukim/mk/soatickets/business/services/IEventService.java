package finki.ukim.mk.soatickets.business.services;

import finki.ukim.mk.soatickets.business.view.models.events.CreateEventViewModel;
import finki.ukim.mk.soatickets.business.view.models.events.UpdateEventViewModel;
import finki.ukim.mk.soatickets.business.view.models.events.EventViewModel;

import java.util.Date;
import java.util.List;

public interface IEventService {
    List<EventViewModel> getAll();
    EventViewModel getById(Long eventId) throws Exception;
    Long create(CreateEventViewModel event);
    Long update(UpdateEventViewModel event);
    Long delete(Long eventId) throws Exception;
    List<EventViewModel> findAllByName(String name) throws Exception;
    List<EventViewModel> findAllByDate(Date date) throws Exception;
    List<EventViewModel> findAllByLocation(String location) throws Exception;
}
