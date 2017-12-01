package finki.ukim.mk.soatickets.repositories;

import finki.ukim.mk.soatickets.models.tickets.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ITicketRepository extends CrudRepository<Ticket, Long> {
    public List<Ticket> findAllByEventId(long eventId);
    //public void createTicketForEvent(Ticket ticket);
    //public void deleteTicketsForEvent(long eventId);
    //public void updatePriceForEvent(long eventId, int price);
}
