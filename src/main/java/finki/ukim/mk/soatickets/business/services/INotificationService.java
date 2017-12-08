package finki.ukim.mk.soatickets.business.services;

import finki.ukim.mk.soatickets.business.view.models.user.NotificationViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;

import java.util.List;

/**
 * Created by DarkoM on 08.12.2017.
 */
public interface INotificationService {
    List<NotificationViewModel> getAllForUser(long userId) throws Exception;
}
