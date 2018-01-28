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

import finki.ukim.mk.soatickets.business.services.IMessageService;
import finki.ukim.mk.soatickets.business.services.INotificationService;
import finki.ukim.mk.soatickets.business.view.models.messages.CreateMessageViewModel;
import finki.ukim.mk.soatickets.business.view.models.messages.MessageViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;
import finki.ukim.mk.soatickets.models.messages.Message;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.IMessageRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;
import finki.ukim.mk.soatickets.business.services.implementation.MessageService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 *  Unit tests for {@link MessageService}
 */
@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTest {

  @Mock
  private IMessageRepository messageRepository;

  @Mock
  private IUserRepository userRepository;

  @Mock
  private INotificationService notificationService;

  @InjectMocks
  private IMessageService messageService = new MessageService();

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

    // create recieved message
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");;
    Date recievedDate = dateFormat.parse("02-02-2018");
    Message recievedMessage = new Message();
    recievedMessage.setMessage("message recieved");
    recievedMessage.setDate(recievedDate);
    recievedMessage.setReceiver(user);
    recievedMessage.setId(Long.valueOf(22));
    List<Message> recievedMessages = new ArrayList<>(2);
    recievedMessages.add(recievedMessage);
    user.setReceivedMessages(recievedMessages);

    // create sent message
    Message sentMessage = new Message();
    sentMessage.setMessage("message sent");
    Date sentDate = dateFormat.parse("02-03-2018");
    sentMessage.setDate(sentDate);
    sentMessage.setSender(user);
    sentMessage.setId(Long.valueOf(23));
    List<Message> sentMessages = new ArrayList<>(2);
    sentMessages.add(sentMessage);
    user.setSendMessages(sentMessages);

    when(userRepository.findOne(Long.valueOf(1))).thenReturn(user);
    when(messageRepository.findOne(Long.valueOf(22))).thenReturn(recievedMessage);
    when(messageRepository.getAllByReceiverId(Long.valueOf(1))).thenReturn(recievedMessages);
    when(messageRepository.getAllBySenderId(Long.valueOf(1))).thenReturn(sentMessages);
    when(messageRepository.save(any(Message.class))).thenReturn(sentMessage);
  }

  @Test
  public void shouldGetAllMessagesForSender() {
    // when
    List<MessageViewModel> messagesForSender = messageService.getAllMessagesForSender(Long.valueOf(1));

    // then
    MessageViewModel message = messagesForSender.get(0);
    Assert.assertThat(message, Matchers.notNullValue());

    String messageContent = message.getMessage();
    Assert.assertThat(messageContent, Matchers.equalTo("message sent"));

    UserViewModel messageSender = message.getSender();
    String messageSenderEmail = messageSender.getEmail();
    Assert.assertThat(messageSenderEmail, Matchers.equalTo("EMAIL"));

    String messageSenderFirstName = messageSender.getFirstName();
    Assert.assertThat(messageSenderFirstName, Matchers.equalTo("FIRST NAME"));

    String messageSenderLastName = messageSender.getLastName();
    Assert.assertThat(messageSenderLastName, Matchers.equalTo("LAST NAME"));

    String phoneNumber = messageSender.getPhoneNumber();
    Assert.assertThat(phoneNumber, Matchers.equalTo("023354235"));
  }

  @Test
  public void shouldGetAllMessagesForReciever() {
    // when
    List<MessageViewModel> messagesForSender = messageService.getAllMessagesForReceiver(Long.valueOf(1));

    // then
    MessageViewModel message = messagesForSender.get(0);
    Assert.assertThat(message, Matchers.notNullValue());

    String messageContent = message.getMessage();
    Assert.assertThat(messageContent, Matchers.equalTo("message recieved"));

    UserViewModel messageReciever = message.getReceiver();
    String messageSenderEmail = messageReciever.getEmail();
    Assert.assertThat(messageSenderEmail, Matchers.equalTo("EMAIL"));

    String messageSenderFirstName = messageReciever.getFirstName();
    Assert.assertThat(messageSenderFirstName, Matchers.equalTo("FIRST NAME"));

    String messageSenderLastName = messageReciever.getLastName();
    Assert.assertThat(messageSenderLastName, Matchers.equalTo("LAST NAME"));

    String phoneNumber = messageReciever.getPhoneNumber();
    Assert.assertThat(phoneNumber, Matchers.equalTo("023354235"));
  }

  @Test
  public void shouldCreateMessage() throws Exception{

    // Given
    CreateMessageViewModel messageViewModel = new CreateMessageViewModel();
    messageViewModel.setSenderId(Long.valueOf(1));
    messageViewModel.setReceiverId(Long.valueOf(1));
    messageViewModel.setSubject("SKIT");
    messageViewModel.setMessage("TESTS");
    messageViewModel.setId(Long.valueOf(23));

    // when
    Long newMessage = messageService.create(messageViewModel);

    // then
    Assert.assertThat(newMessage, Matchers.equalTo(Long.valueOf(23)));
  }

  @Test
  public void shouldDeleteMessage() throws Exception{

    // Given
    CreateMessageViewModel messageViewModel = new CreateMessageViewModel();
    messageViewModel.setSenderId(Long.valueOf(1));
    messageViewModel.setReceiverId(Long.valueOf(1));
    messageViewModel.setSubject("SKIT");
    messageViewModel.setMessage("TESTS");

    // when
    Long newMessage = messageService.delete(Long.valueOf(22));

    // then
    Assert.assertThat(newMessage, Matchers.equalTo(Long.valueOf(22)));
  }

  @Test(expected = Exception.class)
  public void shouldThrowExceptionIfUserNotExists() throws Exception{
    // Given
    CreateMessageViewModel messageViewModel = new CreateMessageViewModel();
    messageViewModel.setSenderId(Long.valueOf(3));
    messageViewModel.setReceiverId(Long.valueOf(1));
    messageViewModel.setSubject("SKIT");
    messageViewModel.setMessage("TESTS");
    Long newMessage = null;
    // when
    try {
      newMessage = messageService.create(messageViewModel);
    } finally {
      // then
      Assert.assertThat(newMessage, Matchers.nullValue());
    }
  }
}
