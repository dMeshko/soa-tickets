package finki.ukim.mk.soatickets.business.services;

import finki.ukim.mk.soatickets.business.view.models.tickets.BoughtTicketViewModel;

import java.util.List;

public interface IBoughtTicketService {
    public List<BoughtTicketViewModel> getAllForUser(long userId) throws Exception;
    public List<BoughtTicketViewModel> getAllForEvent(long eventId);
}
