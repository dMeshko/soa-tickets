package finki.ukim.mk.soatickets.repositories;

import finki.ukim.mk.soatickets.models.events.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface IEventRepository extends CrudRepository<Event, Long> {
    Event findByName(String name);
    Event findByLocation(String location);
    Event findByDate(Date date);
}
