package finki.ukim.mk.soatickets.business.services.implementation;

import finki.ukim.mk.soatickets.business.services.INotificationService;
import finki.ukim.mk.soatickets.business.view.models.LookupViewModel;
import finki.ukim.mk.soatickets.business.view.models.blog.PostViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.NotificationViewModel;
import finki.ukim.mk.soatickets.models.blog.Post;
import finki.ukim.mk.soatickets.models.user.Notification;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.INotificationRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DarkoM on 08.12.2017.
 */

@Service
public class NotificationService implements INotificationService {
    @Autowired
    private INotificationRepository notificationRepository;

    @Autowired
    private IUserRepository userRepository;

    private ModelMapper modelMapper;

    public NotificationService(){
        modelMapper = new ModelMapper();
    }

    @Override
    public List<NotificationViewModel> getAllForUser(long userId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null)
            throw new Exception("User not found!");

        List<NotificationViewModel> result = new ArrayList<>();
        for (Notification notification : user.getReceivedNotifications())
        {
            NotificationViewModel notificationViewModel = modelMapper.map(notification, NotificationViewModel.class);
            LookupViewModel<Long> userFromViewModel = new LookupViewModel<>(notification.getUserFrom().getId(), notification.getUserFrom().toString());
            notificationViewModel.setUserFrom(userFromViewModel);
            result.add(notificationViewModel);
        }

        return result;
    }
}
