package finki.ukim.mk.soatickets.controllers;

import finki.ukim.mk.soatickets.business.services.ISupportTicketService;
import finki.ukim.mk.soatickets.repositories.ISupportTicketRepository;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

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

    MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(mockSupportTicketController).build();
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

        mockMvc.perform(post("/api/support/").accept(MediaType.APPLICATION_JSON).content(contetnts)).andExpect(status().isOk());
    }

}
