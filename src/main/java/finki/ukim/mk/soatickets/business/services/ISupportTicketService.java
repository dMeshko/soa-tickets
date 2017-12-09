package finki.ukim.mk.soatickets.business.services;

import finki.ukim.mk.soatickets.business.view.models.support.SupportTicketViewModel;

import java.util.List;

/**
 * Created by DarkoM on 09.12.2017.
 */
public interface ISupportTicketService {
    long createSupportTicket(long userId, String content) throws Exception;
    List<SupportTicketViewModel> getAllSupportTickets();
}
