package finki.ukim.mk.soatickets.business.services;

import finki.ukim.mk.soatickets.business.view.models.notifications.NotificationViewModel;

import java.util.List;

public interface INotificationService {

    List <NotificationViewModel> getUserNotifications(Long userId) throws Exception;
}
