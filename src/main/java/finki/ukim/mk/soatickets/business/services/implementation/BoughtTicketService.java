package finki.ukim.mk.soatickets.business.services.implementation;

import finki.ukim.mk.soatickets.business.services.IBoughtTicketService;
import finki.ukim.mk.soatickets.business.view.models.tickets.BoughtTicketViewModel;
import finki.ukim.mk.soatickets.models.tickets.BoughtTicket;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.IBoughtTicketRepository;
import finki.ukim.mk.soatickets.repositories.IEventRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoughtTicketService implements IBoughtTicketService {

    private ModelMapper mapper;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IEventRepository eventRepository;

    @Autowired
    private IBoughtTicketRepository boughtTicketRepository;

    public BoughtTicketService() {
        mapper = new ModelMapper();
    }

    @Override
    public List<BoughtTicketViewModel> getAllForUser(long userId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new Exception("User not found");
        }
        List<BoughtTicketViewModel> result = new ArrayList<>();
        for (BoughtTicket boughtTicket : user.getBoughtTickets()) {
            result.add(mapper.map(boughtTicket, BoughtTicketViewModel.class));
        }
        return result;
    }

    @Override
    public List<BoughtTicketViewModel> getAllForEvent(long eventId) {
        List<BoughtTicketViewModel> result = new ArrayList<>();
        for (BoughtTicket boughtTicket : boughtTicketRepository.findByEvent(eventId)) {
            result.add(mapper.map(boughtTicket, BoughtTicketViewModel.class));
        }
        return result;
    }
}
