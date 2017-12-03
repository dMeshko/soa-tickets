package finki.ukim.mk.soatickets.repositories;

import finki.ukim.mk.soatickets.models.tickets.Invoice;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by DarkoM on 03.12.2017.
 */
public interface IInvoiceRepository extends CrudRepository<Invoice, Long> { }
