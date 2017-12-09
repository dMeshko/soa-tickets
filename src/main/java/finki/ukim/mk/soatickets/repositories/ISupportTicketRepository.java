package finki.ukim.mk.soatickets.repositories;

import finki.ukim.mk.soatickets.models.support.SupportTicket;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by DarkoM on 09.12.2017.
 */
public interface ISupportTicketRepository extends CrudRepository<SupportTicket, Long> {

}
