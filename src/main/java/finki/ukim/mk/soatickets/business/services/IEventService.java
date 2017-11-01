package finki.ukim.mk.soatickets.business.services;

import finki.ukim.mk.soatickets.business.view.models.events.CreateEventViewModel;
import finki.ukim.mk.soatickets.business.view.models.events.UpdateEventViewModel;
import finki.ukim.mk.soatickets.business.view.models.events.EventViewModel;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IEventService {
    List<EventViewModel> getAll();
    EventViewModel getById(Long eventId) throws Exception;
    Long create(CreateEventViewModel event) throws Exception;
    Long update(UpdateEventViewModel event) throws Exception;
    Long delete(Long eventId) throws Exception;
    List<EventViewModel> findAllByName(String name) throws Exception;
    List<EventViewModel> findAllByDate(String date) throws Exception;
    List<EventViewModel> findAllByLocation(String location) throws Exception;
}
