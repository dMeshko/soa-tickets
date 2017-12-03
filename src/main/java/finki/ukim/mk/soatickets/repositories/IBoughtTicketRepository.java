package finki.ukim.mk.soatickets.repositories;

import finki.ukim.mk.soatickets.models.tickets.BoughtTicket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IBoughtTicketRepository extends CrudRepository<BoughtTicket, Long> {
    List<BoughtTicket> findByEvent(long eventId);
}
