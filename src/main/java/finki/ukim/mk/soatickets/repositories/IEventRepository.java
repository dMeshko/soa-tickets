package finki.ukim.mk.soatickets.repositories;

import finki.ukim.mk.soatickets.business.view.models.events.EventViewModel;
import finki.ukim.mk.soatickets.models.events.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface IEventRepository extends CrudRepository<Event, Long> {
    List<Event> findAllByName(String name);
    List<Event> findAllByLocation(String location);
    List<Event> findAllByDate(Date date);
}
