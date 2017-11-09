package finki.ukim.mk.soatickets.business.services.implementation;

import finki.ukim.mk.soatickets.business.services.ITicketsService;
import finki.ukim.mk.soatickets.business.view.models.tickets.TicketViewModel;
import finki.ukim.mk.soatickets.models.events.Event;
import finki.ukim.mk.soatickets.models.tickets.Ticket;
import finki.ukim.mk.soatickets.repositories.IEventRepository;
import finki.ukim.mk.soatickets.repositories.ITicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketsService implements ITicketsService {

    @Autowired
    private IEventRepository eventRepository;

    private ModelMapper modelMapper;

    public TicketsService() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<TicketViewModel> getAllForEvent(long eventId) throws Exception {
        Event event = eventRepository.findOne(eventId);
        if (event == null) {
            throw new Exception("Event not found");
        }
        List<TicketViewModel> result = new ArrayList<>();
        for (Ticket ticket : event.getTickets()) {
            result.add(modelMapper.map(ticket, TicketViewModel.class));
        }

        return result;
    }
}
