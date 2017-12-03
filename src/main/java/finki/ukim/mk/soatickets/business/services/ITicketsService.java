package finki.ukim.mk.soatickets.business.services;

import finki.ukim.mk.soatickets.business.view.models.tickets.PurchaseTicketViewModel;
import finki.ukim.mk.soatickets.business.view.models.tickets.TicketViewModel;
import finki.ukim.mk.soatickets.models.events.Event;
import finki.ukim.mk.soatickets.models.tickets.Ticket;

import java.util.List;

public interface ITicketsService {
    public List<TicketViewModel> getAllForEvent(long eventId) throws Exception;
    long deleteTicket(long eventId, long ticketId) throws Exception;
    long createTicketForEvent(long eventId, int price) throws Exception;
    long removeTicketsForEvent(long eventId) throws Exception;
    long updatePriceForEvent(long eventId, int price) throws Exception;
    long purchaseTicket(PurchaseTicketViewModel model) throws Exception;
}
