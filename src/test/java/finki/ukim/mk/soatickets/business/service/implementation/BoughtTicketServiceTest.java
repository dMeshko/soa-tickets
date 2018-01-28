package finki.ukim.mk.soatickets.business.service.implementation;
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

import finki.ukim.mk.soatickets.business.services.IBoughtTicketService;
import finki.ukim.mk.soatickets.business.services.implementation.BoughtTicketService;
import finki.ukim.mk.soatickets.business.view.models.events.EventViewModel;
import finki.ukim.mk.soatickets.business.view.models.tickets.BoughtTicketViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;
import finki.ukim.mk.soatickets.models.events.Event;
import finki.ukim.mk.soatickets.models.tickets.BoughtTicket;
import finki.ukim.mk.soatickets.models.tickets.Ticket;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.IEventRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;

import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link BoughtTicketService}
 */
@RunWith(MockitoJUnitRunner.class)
public class BoughtTicketServiceTest {

  @Mock
  private IUserRepository userRepository;

  @Mock
  private IEventRepository eventRepository;


  @InjectMocks
  private IBoughtTicketService boughtTicketService = new BoughtTicketService();

  @Before
  public void setup(){
    // Given
    // create user
    User user = new User();
    user.setFirstName("FIRST NAME");
    user.setLastName("LAST NAME");
    user.setEmail("EMAIL");
    user.setPhoneNumber("023354235");
    user.setPassword("password");
    user.setActive(true);
    user.setId(Long.valueOf(1));

    // create Event
    Date date = new Date();
    Event event = new Event(user,"Event name", "description", "location", date);

    // create ticket
    Ticket ticket = new Ticket(event,Float.valueOf(300));
    List<Ticket> tickets = new ArrayList<>(2);
    tickets.add(ticket);
    event.setTickets(tickets);
    // create bought ticket
    BoughtTicket boughtTicket = new BoughtTicket(user, ticket);
    List<BoughtTicket> boughtTickets = new ArrayList<>(2);
    boughtTickets.add(boughtTicket);
    event.setBoughtTickets(boughtTickets);

    user.setBoughtTickets(boughtTickets);

    when(userRepository.findOne(Long.valueOf(1))).thenReturn(user);
    when(eventRepository.findOne(Long.valueOf(2))).thenReturn(event);
  }

  @Test
  public void shouldGetAllBoughtTicketsForUser() throws Exception {

    // When
    List<BoughtTicketViewModel> allForUser = boughtTicketService.getAllForUser(Long.valueOf(1));

    // Then
    BoughtTicketViewModel boughtTicketViewModel = allForUser.get(0);
    EventViewModel event = boughtTicketViewModel.getEvent();

    String date = event.getDate();
    Assert.assertThat(date, Matchers.notNullValue());

    String description = event.getDescription();
    Assert.assertThat(description, Matchers.equalTo("description"));

    String eventName = event.getName();
    Assert.assertThat(eventName, Matchers.equalTo("Event name"));

    String location = event.getLocation();
    Assert.assertThat(location, Matchers.equalTo("location"));

    UserViewModel owner = boughtTicketViewModel.getOwner();
    Assert.assertThat(owner, Matchers.notNullValue());
  }

  @Test
  public void shouldGetAllBoughtTicketsForEvent() throws Exception {

    // When
    List<BoughtTicketViewModel> allForEvent = boughtTicketService.getAllForEvent(2);

    // Then
    BoughtTicketViewModel boughtTicketViewModel = allForEvent.get(0);
    EventViewModel event = boughtTicketViewModel.getEvent();

    String date = event.getDate();
    Assert.assertThat(date, Matchers.notNullValue());

    String description = event.getDescription();
    Assert.assertThat(description, Matchers.equalTo("description"));

    String eventName = event.getName();
    Assert.assertThat(eventName, Matchers.equalTo("Event name"));

    String location = event.getLocation();
    Assert.assertThat(location, Matchers.equalTo("location"));

    UserViewModel owner = boughtTicketViewModel.getOwner();
    Assert.assertThat(owner, Matchers.notNullValue());
  }

  @Test(expected = Exception.class)
  public void shouldGetExceptionWhenUserNotExists() throws Exception{
    List<BoughtTicketViewModel> allForUser = null;
    // When
    try{
     allForUser = boughtTicketService.getAllForUser(Long.valueOf(6));

    } finally {
      // Then
      Assert.assertThat(allForUser, Matchers.nullValue());
    }
  }

  @Test(expected = Exception.class)
  public void shouldGetExceptionWhenEventNotExists() throws Exception{
    List<BoughtTicketViewModel> allForEvent = null;
    // When
    try{
      allForEvent = boughtTicketService.getAllForEvent(Long.valueOf(6));

    } finally {
      // Then
      Assert.assertThat(allForEvent, Matchers.nullValue());
    }
  }
}
