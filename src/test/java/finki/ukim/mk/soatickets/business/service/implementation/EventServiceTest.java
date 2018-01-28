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
import finki.ukim.mk.soatickets.business.services.IEventService;
import finki.ukim.mk.soatickets.business.services.implementation.EventService;
import finki.ukim.mk.soatickets.business.view.models.events.CreateEventViewModel;
import finki.ukim.mk.soatickets.business.view.models.events.EventViewModel;

import finki.ukim.mk.soatickets.business.view.models.events.UpdateEventViewModel;
import finki.ukim.mk.soatickets.models.events.Event;
import finki.ukim.mk.soatickets.models.tickets.BoughtTicket;
import finki.ukim.mk.soatickets.models.tickets.Ticket;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.IEventRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link EventService}
 */
@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

  @Mock
  private IEventRepository eventRepository;
  @Mock
  private IUserRepository userRepository;

  @InjectMocks
  private IEventService eventService = new EventService();

  @Before
  public void setup() throws Exception{

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
    // create bought ticket
    BoughtTicket boughtTicket = new BoughtTicket(user, ticket);
    List<BoughtTicket> boughtTickets = new ArrayList<>(2);
    boughtTickets.add(boughtTicket);
    event.setBoughtTickets(boughtTickets);

    user.setBoughtTickets(boughtTickets);

    when(userRepository.findOne(Long.valueOf(1))).thenReturn(user);
    when(eventRepository.findOne(Long.valueOf(2))).thenReturn(event);
    when(eventRepository.findAllByName("Event name")).thenReturn(events);
    when(eventRepository.findAllByLocation("location")).thenReturn(events);
    when(eventRepository.findAllByDate(date)).thenReturn(events);
    when(eventRepository.findOne(Long.valueOf(2))).thenReturn(event);
    when(eventRepository.findAll()).thenReturn(events);
    when(eventRepository.save(any(Event.class))).thenReturn(event);
  }

  @Test
  public void shouldGetAllEvents(){

    // when
    List<EventViewModel> eventServiceAll = eventService.getAll();

    // then
    EventViewModel event = eventServiceAll.get(0);
    String date = event.getDate();
    Assert.assertThat(date, Matchers.notNullValue());

    String description = event.getDescription();
    Assert.assertThat(description, Matchers.equalTo("description"));

    String eventName = event.getName();
    Assert.assertThat(eventName, Matchers.equalTo("Event name"));

    String location = event.getLocation();
    Assert.assertThat(location, Matchers.equalTo("location"));
  }

  @Test
  public void shouldGetEventById() throws Exception {

    // when
    EventViewModel event = eventService.getById(Long.valueOf(2));

    // then
    String date = event.getDate();
    Assert.assertThat(date, Matchers.notNullValue());

    String description = event.getDescription();
    Assert.assertThat(description, Matchers.equalTo("description"));

    String eventName = event.getName();
    Assert.assertThat(eventName, Matchers.equalTo("Event name"));

    String location = event.getLocation();
    Assert.assertThat(location, Matchers.equalTo("location"));
  }

  @Test
  public void shouldCreateNewEvent() throws Exception{
    // given
    CreateEventViewModel eventViewModel = new CreateEventViewModel();
    eventViewModel.setDate("02-02-2018");
    eventViewModel.setDescription("description");
    eventViewModel.setName("event's name");
    eventViewModel.setOwnerId(Long.valueOf(1));
    eventViewModel.setLocation("skopje");

    // when
    Long newEvent = eventService.create(eventViewModel);

    // then
    Assert.assertThat(newEvent, Matchers.equalTo(Long.valueOf(2)));
  }

  @Test(expected = Exception.class)
  public void shouldThrowExceptionIfDateIsInPast() throws Exception{
    // given
    CreateEventViewModel eventViewModel = new CreateEventViewModel();
    eventViewModel.setDate("02-02-1995");
    eventViewModel.setDescription("description");
    eventViewModel.setName("event's name");
    eventViewModel.setOwnerId(Long.valueOf(1));
    eventViewModel.setLocation("skopje");
    Long newEvent = null;
    // when
    try {
      newEvent = eventService.create(eventViewModel);
    } finally {
      // then
      Assert.assertThat(newEvent, Matchers.nullValue());
    }
  }

  @Test(expected = Exception.class)
  public void shouldThrowExceptionIfUserNotExists() throws Exception{
    // given
    CreateEventViewModel eventViewModel = new CreateEventViewModel();
    eventViewModel.setDate("02-02-2018");
    eventViewModel.setDescription("description");
    eventViewModel.setName("event's name");
    eventViewModel.setOwnerId(Long.valueOf(4));
    eventViewModel.setLocation("skopje");
    Long newEvent = null;
    // when
    try {
      newEvent = eventService.create(eventViewModel);
    } finally {
      // then
      Assert.assertThat(newEvent, Matchers.nullValue());
    }
  }

  @Test(expected = Exception.class)
  public void shouldThrowExceptionIfEventNotExists() throws Exception{
    EventViewModel event = null;
    // when
    try {
      event = eventService.getById(Long.valueOf(23));
    } finally {
      // then
      Assert.assertThat(event, Matchers.nullValue());
    }
  }

  @Test
  public void shouldUpdateEvent() throws Exception{
    // given
    UpdateEventViewModel eventViewModel = new UpdateEventViewModel();
    eventViewModel.setDate("02-02-2018");
    eventViewModel.setDescription("description");
    eventViewModel.setName("event's name");
    eventViewModel.setOwnerId(Long.valueOf(1));
    eventViewModel.setLocation("skopje");
    eventViewModel.setId(Long.valueOf(2));

    // when
    Long updateEvent = eventService.update(eventViewModel);

    // then
    Assert.assertThat(updateEvent, Matchers.equalTo(Long.valueOf(2)));
  }

  @Test
  public void shouldDeleteEvent() throws Exception{
    // when
    Long updateEvent = eventService.delete(Long.valueOf(2));

    // then
    Assert.assertThat(updateEvent, Matchers.equalTo(Long.valueOf(2)));
  }

  @Test
  public void shouldGetAllEventsByName() throws Exception{

    // when
    List<EventViewModel> eventServiceAll = eventService.findAllByName("Event name");

    // then
    EventViewModel event = eventServiceAll.get(0);
    String date = event.getDate();
    Assert.assertThat(date, Matchers.notNullValue());

    String description = event.getDescription();
    Assert.assertThat(description, Matchers.equalTo("description"));

    String eventName = event.getName();
    Assert.assertThat(eventName, Matchers.equalTo("Event name"));

    String location = event.getLocation();
    Assert.assertThat(location, Matchers.equalTo("location"));
  }

  @Test
  public void shouldGetAllEventsByDate() throws Exception{

    // when
    List<EventViewModel> eventServiceAll = eventService.findAllByDate("02-02-2018");

    // then
    EventViewModel event = eventServiceAll.get(0);
    String date = event.getDate();
    Assert.assertThat(date, Matchers.equalTo("02-02-2018"));

    String description = event.getDescription();
    Assert.assertThat(description, Matchers.equalTo("description"));

    String eventName = event.getName();
    Assert.assertThat(eventName, Matchers.equalTo("Event name"));

    String location = event.getLocation();
    Assert.assertThat(location, Matchers.equalTo("location"));
  }

  @Test
  public void shouldGetAllEventsByLocation() throws Exception{

    // when
    List<EventViewModel> eventServiceAll = eventService.findAllByLocation("location");

    // then
    EventViewModel event = eventServiceAll.get(0);
    String date = event.getDate();
    Assert.assertThat(date, Matchers.notNullValue());

    String description = event.getDescription();
    Assert.assertThat(description, Matchers.equalTo("description"));

    String eventName = event.getName();
    Assert.assertThat(eventName, Matchers.equalTo("Event name"));

    String location = event.getLocation();
    Assert.assertThat(location, Matchers.equalTo("location"));
  }

}
