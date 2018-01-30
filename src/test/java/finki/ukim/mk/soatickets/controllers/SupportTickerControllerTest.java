package finki.ukim.mk.soatickets.controllers;

import finki.ukim.mk.soatickets.business.services.ISupportTicketService;
import finki.ukim.mk.soatickets.business.services.IUsersService;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;
import finki.ukim.mk.soatickets.repositories.ISupportTicketRepository;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class SupportTickerControllerTest {

    @InjectMocks
    SupportTicketsController mockSupportTicketController;

    @Mock
    ISupportTicketService mockSupportTicketService;

    @Mock
    ISupportTicketRepository mockSupportTicketRepository;

    @Mock
    IUsersService mockUserService;

    MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        UserViewModel userViewModel = new UserViewModel();
        userViewModel.setId(Long.valueOf(1));
        userViewModel.setActive(true);
        userViewModel.setFirstName("Test");
        userViewModel.setLastName("Tester");
        userViewModel.setEmail("spring@test.mk");
        userViewModel.setPhoneNumber("123456789");

        Mockito.when(mockUserService.findByEmail("user")).thenReturn(userViewModel);
        Mockito.when(mockUserService.findByUsername("user")).thenReturn(userViewModel);
        Mockito.when(mockSupportTicketService.createSupportTicket(any(Long.class), any(String.class))).thenReturn(Long.valueOf(1));

        mockMvc = MockMvcBuilders.standaloneSetup(mockSupportTicketController).build();
    }

    private void when(IUsersService mockUserService) {
    }

    @Test
    public void shouldReturnTestTickets() throws Exception {
        mockMvc.perform(get("/api/support/")).andExpect(status().isOk());
        verify(mockSupportTicketService, times(1)).getAllSupportTickets();
    }

    @Test
    public void shouldRegisterTicket() throws Exception {
        String contetnts = new JSONObject().put("content", "Ticket")
                                           .put("createdOn", new Date().toString())
                                           .put("user", "1")
                                           .toString();

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return "user";
            }
        };

        mockMvc.perform(post("/api/support/").accept(MediaType.APPLICATION_JSON)
                                                        .content(contetnts)
                                                        .param("content", "This")
                                                        .principal(principal))
                                                        .andExpect(status().isOk()).andReturn();
    }

}
