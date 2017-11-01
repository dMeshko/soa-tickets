package finki.ukim.mk.soatickets.business.services.implementation;

import finki.ukim.mk.soatickets.business.services.IEventService;
import finki.ukim.mk.soatickets.business.view.models.events.CreateEventViewModel;
import finki.ukim.mk.soatickets.business.view.models.events.EventViewModel;
import finki.ukim.mk.soatickets.business.view.models.events.UpdateEventViewModel;
import finki.ukim.mk.soatickets.models.events.Event;
import finki.ukim.mk.soatickets.repositories.IEventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventService implements IEventService {

    @Autowired
    private IEventRepository eventRepository;

    private ModelMapper modelMapper;

    public EventService() { modelMapper = new ModelMapper(); }

    @Override
    public List<EventViewModel> getAll() {
        List<EventViewModel> result = new ArrayList<>();
        for (Event event : eventRepository.findAll())
            result.add(modelMapper.map(event, EventViewModel.class));

        return result;
    }

    @Override
    public EventViewModel getById(Long eventId) throws Exception {
        Event result = eventRepository.findOne(eventId);

        if(result == null) {
            throw new Exception("There is no such event!");
        }
        return modelMapper.map(result, EventViewModel.class);
    }

    @Override
    public Long register(CreateEventViewModel event) {
        Event eventDbo = new Event(event.getOwnerId(),
                                   event.getName(),
                                   event.getDescrption(),
                                   event.getLocation(),
                                   event.getDate());
        return eventRepository.save(eventDbo).getId();
    }

    @Override
    public Long update(UpdateEventViewModel event) {
        Event dboEvent = eventRepository.findOne(event.getId());
        dboEvent.setName(event.getName());
        dboEvent.setDescrption(event.getDescrption());
        dboEvent.setLocation(event.getLocation());
        dboEvent.setDate(event.getDate());

        return eventRepository.save(dboEvent).getId();
    }

    @Override
    public Long delete(Long eventId) throws Exception {
        Event dboEvent = eventRepository.findOne(eventId);
        if ( dboEvent == null) {
            throw new Exception("No such Event!");
        }
        eventRepository.delete(dboEvent);

        return dboEvent.getId();
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
