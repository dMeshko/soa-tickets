package finki.ukim.mk.soatickets.business.services;

import finki.ukim.mk.soatickets.business.view.models.tickets.InvoiceViewModel;
import finki.ukim.mk.soatickets.models.tickets.PaymentMethod;

import java.util.List;

/**
 * Created by DarkoM on 03.12.2017.
 */
public interface IInvoiceService {
    List<InvoiceViewModel> getAllForUser(long userId) throws Exception;
    long createInvoice(long userId, long eventId, float amountPayed, PaymentMethod paymentMethod) throws Exception;
}