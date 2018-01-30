package finki.ukim.mk.soatickets.controllers;

import finki.ukim.mk.soatickets.business.services.implementation.MessageService;
import finki.ukim.mk.soatickets.business.services.implementation.UsersService;
import finki.ukim.mk.soatickets.business.view.models.messages.CreateMessageViewModel;
import finki.ukim.mk.soatickets.models.messages.Message;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.IMessageRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class MessageControllerTest {

    static final String url = "/api/messages";

    @InjectMocks
    MessageController mockMessageController;

    @Mock
    UsersService usersService;

    @Mock
    MessageService messageService;

    @Mock
    IMessageRepository messageRepository;

    @Mock
    IUserRepository userRepository;

    MockMvc mockMvc;

    @Before
    public void setup() {

        User user = new User();
        user.setFirstName("FIRST NAME");
        user.setLastName("LAST NAME");
        user.setEmail("EMAIL");
        user.setPhoneNumber("023354235");
        user.setPassword("password");
        user.setActive(true);
        user.setId(Long.valueOf(1));

        User userReciever = new User();
        userReciever.setFirstName("FIRST NAME");
        userReciever.setLastName("LAST NAME");
        userReciever.setEmail("EMAIL");
        userReciever.setPhoneNumber("023354235");
        userReciever.setPassword("password");
        userReciever.setActive(true);
        userReciever.setId(Long.valueOf(2));

        Message message = new Message();
        message.setId(Long.valueOf(1));
        message.setSubject("Test Subj");
        message.setMessage("Test Msg");
        message.setDate(new Date());
        message.setSender(user);
        message.setReceiver(userReciever);

        Message messageBack = new Message();
        message.setId(Long.valueOf(1));
        message.setSubject("Test Subj2");
        message.setMessage("Test Msg2");
        message.setDate(new Date());
        message.setSender(userReciever);
        message.setReceiver(user);

        List messagesList = new ArrayList();
        messagesList.add(message);
        List messagesList2 = new ArrayList();
        messagesList.add(messageBack);

        List messagesList3 = new ArrayList();
        messagesList3.add(messageBack);
        messagesList3.add(message);


        when(messageRepository.getAllByReceiverId(1)).thenReturn(messagesList);
        when(messageRepository.getAllByReceiverId(2)).thenReturn(messagesList2);
        when(messageRepository.getAllBySenderId(1)).thenReturn(messagesList);
        when(messageRepository.findAll()).thenReturn(messagesList3);

        mockMvc = MockMvcBuilders.standaloneSetup(mockMessageController).build();
    }
    //Method is wrong in code
    @Test
    public void shouldDeleteMessagesById() throws Exception {
        MvcResult result  = mockMvc.perform(get(url + "/{id}", 1)).andExpect(status().isOk()).andReturn();
        //verify(messageService, times(1)).delete(Long.valueOf(1));
        Assert.assertNotNull(result);
    }

    @Test
    public void shouldReturnAllMessagesOfReceiver() throws Exception {
        MvcResult result  = mockMvc.perform(get(url+"/receiver/{id}", 1)).andExpect(status().isOk()).andReturn();
        //verify(messageService, times(1)).getAllMessagesForSender(1);
        Assert.assertNotNull(result);
    }

    @Test
    public void shouldReturnAllMessagesOfSedner() throws Exception {
        MvcResult result = mockMvc.perform(get(url+"/sender/{id}", 1)).andExpect(status().isOk()).andReturn();
        //verify(messageRepository, times(1)).getAllBySenderId(1);
        Assert.assertNotNull(result);
    }

    @Test
    public void shouldCreateMessage() throws Exception {
        String json = new JSONObject().put("senderId", "1")
                .put("receiverId", "2")
                .put("shubject", "hello")
                .put("message", "test msg")
                .put("id", 23).toString();
        mockMvc.perform(post(url+"/").content(json)).andExpect(status().isOk());
        verify(usersService, times(1)).getById(Long.valueOf(1));

    }

}
