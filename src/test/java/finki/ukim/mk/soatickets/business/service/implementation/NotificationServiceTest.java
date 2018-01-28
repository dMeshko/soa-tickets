package finki.ukim.mk.soatickets.business.service.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.ast.Not;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import finki.ukim.mk.soatickets.business.view.models.LookupViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.NotificationViewModel;
import finki.ukim.mk.soatickets.models.tickets.Invoice;
import finki.ukim.mk.soatickets.models.user.Notification;
import finki.ukim.mk.soatickets.business.services.INotificationService;
import finki.ukim.mk.soatickets.business.services.implementation.NotificationService;
import finki.ukim.mk.soatickets.business.view.models.user.NotificationType;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.INotificationRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 *  Unit test for {@link NotificationServiceTest}
 */
@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceTest {

  @Mock
  private INotificationRepository notificationRepository;

  @Mock
  private IUserRepository userRepository;

  @InjectMocks
  private INotificationService notificationService = new NotificationService();

  @Before
  public void setup() {
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

    Notification notification = new Notification("content", user, user);
    notification.setId(Long.valueOf(2));

    List<Notification> receivedNotifications = new ArrayList<>(2);
    receivedNotifications.add(notification);
    user.setReceivedNotifications(receivedNotifications);

    when(userRepository.findOne(Long.valueOf(1))).thenReturn(user);
    when(notificationRepository.save(any(Notification.class))).thenReturn(notification);

  }
  @Test
  public void shouldGetAllNotificationsForUser() throws Exception {
    //  when
    List<NotificationViewModel> notificationViewModels = notificationService.getAllForUser(Long.valueOf(1));

    // Then
    NotificationViewModel notification = notificationViewModels.get(0);

    Assert.assertThat(notification, Matchers.notNullValue());
    String content = notification.getContent();
    Assert.assertThat(content, Matchers.equalTo("content"));

    LookupViewModel<Long> userFrom = notification.getUserFrom();
    String userFromName = userFrom.getName();
    Assert.assertThat(userFromName, Matchers.equalTo("FIRST NAME LAST NAME"));
  }

  @Test
  public void shouldSendNotification() throws Exception {
    //  when
    long notification = notificationService.sendNotification(Long.valueOf(1), Long.valueOf(1), NotificationType.Comment);

    // Then
    Assert.assertThat(notification, Matchers.equalTo(Long.valueOf(2)));
  }

  @Test(expected =  Exception.class)
  public void shouldThrowExceptionForInvalidUser() throws Exception {

    Long notification = null;
    //  when
    try {
      notification = notificationService.sendNotification(Long.valueOf(2), Long.valueOf(1), NotificationType.valueOf("NEWTYPE"));
    } finally {
      // Then
      Assert.assertThat(notification, Matchers.nullValue());
    };
  }

  @Test(expected =  Exception.class)
  public void shouldThrowExceptionForInvalidNotification() throws Exception {

    Long notification = null;
    //  when
    try {
      notification = notificationService.sendNotification(Long.valueOf(1), Long.valueOf(1), NotificationType.valueOf("NEWTYPE"));
    } finally {
      // Then
      Assert.assertThat(notification, Matchers.nullValue());
    }
  }
}
