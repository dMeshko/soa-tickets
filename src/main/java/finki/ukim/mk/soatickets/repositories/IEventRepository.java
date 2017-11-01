package finki.ukim.mk.soatickets.repositories;

import finki.ukim.mk.soatickets.models.events.Event;

import java.util.Date;

public interface IEventRepository {
    Event findByName(String name);
    Event findByLocation(String location);
    Event findByDate(Date date);
}
