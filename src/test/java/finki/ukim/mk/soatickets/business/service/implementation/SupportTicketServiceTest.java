package finki.ukim.mk.soatickets.business.service.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import finki.ukim.mk.soatickets.business.services.ISupportTicketService;
import finki.ukim.mk.soatickets.business.services.implementation.SupportTicketService;
import finki.ukim.mk.soatickets.business.view.models.LookupViewModel;
import finki.ukim.mk.soatickets.business.view.models.support.SupportTicketViewModel;
import finki.ukim.mk.soatickets.models.support.SupportTicket;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.ISupportTicketRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit tests{@link SupportTicketService}
 */
@RunWith(MockitoJUnitRunner.class)
public class SupportTicketServiceTest {

  @Mock
  private ISupportTicketRepository supportTicketRepository;

  @Mock
  private IUserRepository userRepository;

  @InjectMocks
  private ISupportTicketService supportTicketService = new SupportTicketService();

  @Before
  public void setup() {
    // Given
    User user = new User();
    user.setFirstName("FIRST NAME");
    user.setLastName("LAST NAME");
    user.setEmail("EMAIL");
    user.setPhoneNumber("023354235");
    user.setPassword("password");
    user.setActive(true);
    user.setId(Long.valueOf(1));

    SupportTicket ticket = new SupportTicket("content", user);
    ticket.setId(Long.valueOf(3));
    List<SupportTicket> ticketList = new ArrayList<>(2);
    ticketList.add(ticket);
    user.setSupportTickets(ticketList);
    when(userRepository.findOne(Long.valueOf(1))).thenReturn(user);
    when(supportTicketRepository.findAll()).thenReturn(ticketList);
    when(supportTicketRepository.save(any(SupportTicket.class))).thenReturn(ticket);
  }

  @Test
  public void shouldCreateNewSupportTicket() throws Exception {

    // When
    long supportTicket = supportTicketService.createSupportTicket(Long.valueOf(1), "content");

    // Then
    Assert.assertThat(supportTicket, Matchers.equalTo(Long.valueOf(3)));
  }

  @Test(expected = Exception.class)
  public void shouldThrowExceptionIfContentIsEmpty() throws Exception {
     Long ticket = null;
    // When
    try {
       ticket = supportTicketService.createSupportTicket(Long.valueOf(1), "");
    } finally {
      // Then
      Assert.assertThat(ticket, Matchers.nullValue());
    }
  }
  @Test(expected = Exception.class)
  public void shouldThrowExceptionIfUserIsNotFound() throws Exception {
    Long ticket = null;
    // When
    try {
      ticket = supportTicketService.createSupportTicket(Long.valueOf(4), "content");
    } finally {
      // Then
      Assert.assertThat(ticket, Matchers.nullValue());
    }
  }

  @Test
  public void shouldGetAllSupportTickets(){

    // When
    List<SupportTicketViewModel> supportTickets = supportTicketService.getAllSupportTickets();
    SupportTicketViewModel supportTicketViewModel = supportTickets.get(0);
    String content = supportTicketViewModel.getContent();
    Assert.assertThat(content, Matchers.equalTo("content"));
    LookupViewModel<Long> user = supportTicketViewModel.getUser();
    String userName = user.getName();
    Assert.assertThat(userName, Matchers.equalTo("FIRST NAME LAST NAME"));
  }
}
