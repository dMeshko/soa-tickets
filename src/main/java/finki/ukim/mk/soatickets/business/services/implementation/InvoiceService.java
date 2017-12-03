package finki.ukim.mk.soatickets.business.services.implementation;

import finki.ukim.mk.soatickets.business.services.IEventService;
import finki.ukim.mk.soatickets.business.services.IInvoiceService;
import finki.ukim.mk.soatickets.business.view.models.tickets.InvoiceViewModel;
import finki.ukim.mk.soatickets.models.events.Event;
import finki.ukim.mk.soatickets.models.tickets.Invoice;
import finki.ukim.mk.soatickets.models.tickets.PaymentMethod;
import finki.ukim.mk.soatickets.models.tickets.Ticket;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.IEventRepository;
import finki.ukim.mk.soatickets.repositories.IInvoiceRepository;
import finki.ukim.mk.soatickets.repositories.ITicketRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by DarkoM on 03.12.2017.
 */

@Service
public class InvoiceService implements IInvoiceService {
    @Autowired
    private IInvoiceRepository invoiceRepository;
    @Autowired
    private ITicketRepository ticketRepository;
    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<InvoiceViewModel> getAllForUser(long userId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null)
            throw new Exception("User not found!!");

        List<InvoiceViewModel> mappedInvoices = new ArrayList<>();
        List<Invoice> userInvoices = user.getInvoices();
        mappedInvoices.addAll(userInvoices.stream().map(invoice -> new InvoiceViewModel(user, invoice.getTicket(), invoice.getAmountPayed(), invoice.getPaymentMethod())).collect(Collectors.toList()));

        return mappedInvoices;
    }

    @Override
    public long createInvoice(long userId, long eventId, float amountPayed, PaymentMethod paymentMethod) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null)
            throw new Exception("User not found!!");

        Ticket ticket = ticketRepository.findOne(eventId);
        if (ticket == null)
            throw new Exception("Ticket not found!!");

        Invoice invoice = new Invoice(user, ticket, amountPayed, paymentMethod);
        invoiceRepository.save(invoice);

        return invoice.getId();
    }
}
