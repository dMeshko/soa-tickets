package finki.ukim.mk.soatickets.business.services;

import finki.ukim.mk.soatickets.business.view.models.tickets.TicketViewModel;

import java.util.List;

public interface ITicketsService {
    public List<TicketViewModel> getAllForEvent(long eventId) throws Exception;
}
