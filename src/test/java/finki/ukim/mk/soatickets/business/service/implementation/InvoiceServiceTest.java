package finki.ukim.mk.soatickets.business.service.implementation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import finki.ukim.mk.soatickets.business.services.IInvoiceService;
import finki.ukim.mk.soatickets.business.services.implementation.InvoiceService;
import finki.ukim.mk.soatickets.business.view.models.LookupViewModel;
import finki.ukim.mk.soatickets.business.view.models.tickets.InvoiceViewModel;
import finki.ukim.mk.soatickets.models.events.Event;
import finki.ukim.mk.soatickets.models.tickets.Invoice;
import finki.ukim.mk.soatickets.models.tickets.PaymentMethod;
import finki.ukim.mk.soatickets.models.tickets.Ticket;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.IInvoiceRepository;
import finki.ukim.mk.soatickets.repositories.ITicketRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link InvoiceService}
 */
@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

  @Mock
  private IInvoiceRepository invoiceRepository;

  @Mock
  private ITicketRepository ticketRepository;

  @Mock
  private IUserRepository userRepository;

  @InjectMocks
  private IInvoiceService invoiceService = new InvoiceService();

  @Before
  public void setup() throws Exception{
    // Given
    User user = new User();
    user.setFirstName("FIRST NAME");
    user.setLastName("LAST NAME");
    user.setEmail("EMAIL");
    user.setPhoneNumber("023354235");
    user.setPassword("password");
    user.setActive(true);
    user.setId(Long.valueOf(1));

    // create Event
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");;
    Date date = dateFormat.parse("02-02-2018");
    Event event = new Event(user,"Event name", "description", "location", date);
    event.setId(Long.valueOf(2));
    List<Event> events = new ArrayList<>(2);
    events.add(event);

    // create ticket
    Ticket ticket = new Ticket(event, Float.valueOf(300));
    List<Ticket> tickets = new ArrayList<>(2);
    tickets.add(ticket);
    event.setTickets(tickets);
    List<User> userList = new ArrayList<>();

    // create invoice
    Invoice invoice = new Invoice(user, ticket, Float.valueOf(300), PaymentMethod.Card);
    invoice.setId(Long.valueOf(3));
    List<Invoice> invoices = new ArrayList<>(2);
    invoices.add(invoice);

    user.setInvoices(invoices);
    userList.add(user);

    when(userRepository.findOne(Long.valueOf(1))).thenReturn(user);
    when(ticketRepository.findOne(Long.valueOf(2))).thenReturn(ticket);
    when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);
  }

  @Test
  public void shouldGetAllInvoicesForUser() throws Exception {

    // when
    List<InvoiceViewModel> invoiceViewModels = invoiceService.getAllForUser(Long.valueOf(1));

    // then
    InvoiceViewModel invoiceViewModel = invoiceViewModels.get(0);
    Assert.assertThat(invoiceViewModel, Matchers.notNullValue());

    float amountPayed = invoiceViewModel.getAmountPayed();
    Assert.assertThat(amountPayed, Matchers.equalTo(Float.valueOf(300)));

    LookupViewModel<Integer> paymentMethod = invoiceViewModel.getPaymentMethod();
    String name = paymentMethod.getName();
    Assert.assertThat(name, Matchers.equalTo(PaymentMethod.Card.name()));

    LookupViewModel<Long> ticket = invoiceViewModel.getTicket();
    String ticketName = ticket.getName();
    Assert.assertThat(ticketName, Matchers.equalTo("Event name"));

    LookupViewModel<Long> user = invoiceViewModel.getUser();
    String userName = user.getName();
    Assert.assertThat(userName, Matchers.equalTo("FIRST NAME LAST NAME"));
  }

  @Test
  public void shouldCreateNewInvoice() throws Exception {

    // When
    long id = invoiceService.createInvoice(Long.valueOf(1), Long.valueOf(2), Float.valueOf(300), PaymentMethod.Card);

    // Then
    Assert.assertThat(id, Matchers.equalTo(Long.valueOf(3)));
  }

  @Test(expected = Exception.class)
  public void shouldThrowExceptionWhenUserIsNotFound() throws Exception {
    Long id = null;

    // When
    try {
      id = invoiceService.createInvoice(Long.valueOf(3), Long.valueOf(2), Float.valueOf(300), PaymentMethod.Card);
    } finally {
      // Then
      Assert.assertThat(id, Matchers.nullValue());
    }
  }

  @Test(expected = Exception.class)
  public void shouldThrowExceptionWhenTicketIsNotFound() throws Exception {
    Long id = null;

    // When
    try {
      id = invoiceService.createInvoice(Long.valueOf(3), Long.valueOf(214), Float.valueOf(300), PaymentMethod.Card);
    } finally {
      // Then
      Assert.assertThat(id, Matchers.nullValue());
    }
  }
}
