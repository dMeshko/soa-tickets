package finki.ukim.mk.soatickets.business.services.implementation;

import finki.ukim.mk.soatickets.business.services.IEventService;
import finki.ukim.mk.soatickets.business.view.models.events.CreateEventViewModel;
import finki.ukim.mk.soatickets.business.view.models.events.EventViewModel;
import finki.ukim.mk.soatickets.business.view.models.events.UpdateEventViewModel;
import finki.ukim.mk.soatickets.models.events.Event;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.IEventRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventService implements IEventService {

    @Autowired
    private IEventRepository eventRepository;
    @Autowired
    private IUserRepository userRepository;

    private ModelMapper modelMapper;

    public EventService() { modelMapper = new ModelMapper(); }

    @Override
    public List<EventViewModel> getAll() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        List<EventViewModel> result = new ArrayList<>();
        for (Event event : eventRepository.findAll()){
            EventViewModel viewModel = modelMapper.map(event, EventViewModel.class);
            viewModel.setDate(dateFormat.format(event.getDate()));
            result.add(viewModel);
        }


        return result;
    }

    @Override
    public EventViewModel getById(Long eventId) throws Exception {
        Event result = eventRepository.findOne(eventId);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        if(result == null) {
            throw new Exception("There is no such event!");
        }
        EventViewModel viewModel = modelMapper.map(result, EventViewModel.class);
        viewModel.setDate(dateFormat.format(result.getDate()));
        return viewModel;
    }

    @Override
    public Long create(CreateEventViewModel event) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date eventDate = dateFormat.parse(event.getDate());
        if (eventDate.compareTo(new Date()) < 0)
            throw new Exception("The event can't be in the past.");

        User owner = userRepository.findOne(event.getOwnerId());
        if (owner == null)
            throw new Exception("Owner not found!");

        Event eventDbo = new Event(owner,
                                   event.getName(),
                                   event.getDescription(),
                                   event.getLocation(),
                                   eventDate);
        return eventRepository.save(eventDbo).getId();
    }

    @Override
    public Long update(UpdateEventViewModel event) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date eventDate = dateFormat.parse(event.getDate());
        if (eventDate.compareTo(new Date()) < 0)
            throw new Exception("The event can't be in the past.");

        Event dboEvent = eventRepository.findOne(event.getId());
        dboEvent.setName(event.getName());
        dboEvent.setDescription(event.getDescription());
        dboEvent.setLocation(event.getLocation());
        dboEvent.setDate(eventDate);

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
    public List<EventViewModel> findAllByName(String name) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        List<EventViewModel> result = new ArrayList<>();
        List<Event> dboEvents = eventRepository.findAllByName(name);
        for(Event event : dboEvents) {
            EventViewModel viewModel = modelMapper.map(event, EventViewModel.class);
            viewModel.setDate(dateFormat.format(event.getDate()));
            result.add(viewModel);
        }
        return result;
    }

    @Override
    public List<EventViewModel> findAllByDate(String date) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        List<EventViewModel> result = new ArrayList<>();
        List<Event> dboEvents = eventRepository.findAllByDate(dateFormat.parse(date));
        for(Event event : dboEvents) {
            EventViewModel viewModel = modelMapper.map(event, EventViewModel.class);
            viewModel.setDate(dateFormat.format(event.getDate()));
            result.add(viewModel);
        }
        return result;
    }

    @Override
    public List<EventViewModel> findAllByLocation(String location) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        List<EventViewModel> result = new ArrayList<>();
        List<Event> dboEvents = eventRepository.findAllByLocation(location);
        for(Event event : dboEvents) {
            EventViewModel viewModel = modelMapper.map(event, EventViewModel.class);
            viewModel.setDate(dateFormat.format(event.getDate()));
            result.add(viewModel);
        }
        return result;
    }
}
