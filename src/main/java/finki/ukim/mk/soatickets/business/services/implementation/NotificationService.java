package finki.ukim.mk.soatickets.business.services.implementation;

import finki.ukim.mk.soatickets.business.services.INotificationService;
import finki.ukim.mk.soatickets.business.view.models.LookupViewModel;
import finki.ukim.mk.soatickets.business.view.models.blog.PostViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.NotificationType;
import finki.ukim.mk.soatickets.business.view.models.user.NotificationViewModel;
import finki.ukim.mk.soatickets.models.blog.Post;
import finki.ukim.mk.soatickets.models.user.Notification;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.INotificationRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        List<NotificationViewModel> result = new ArrayList<>();
        for (Notification notification : user.getReceivedNotifications())
        {
            NotificationViewModel notificationViewModel = modelMapper.map(notification, NotificationViewModel.class);
            LookupViewModel<Long> userFromViewModel = new LookupViewModel<>(notification.getUserFrom().getId(), notification.getUserFrom().toString());
            notificationViewModel.setUserFrom(userFromViewModel);
            notificationViewModel.setCreatedAt(dateFormat.format(notification.getCreatedAt()));
            result.add(notificationViewModel);
        }

        return result;
    }

    @Override
    public long sendNotification(long userFromId, long userToId, NotificationType notificationType) throws Exception {
        User userFrom = userRepository.findOne(userFromId);
        if (userFrom == null)
            throw new Exception("Receiver not found!");

        User userTo = userRepository.findOne(userToId);
        if (userTo == null)
            throw new Exception("Sender not found!");

        Date date = new Date();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        String notificationContent = resolveNotificationContent(notificationType);
        String parsedNotificationContent = notificationContent.replace("{0}", userFrom.toString());

        Notification notification = new Notification(parsedNotificationContent, userFrom, userTo);
        notificationRepository.save(notification);

        return notification.getId();
    }

    private String resolveNotificationContent(NotificationType notificationType) throws Exception {
        switch (notificationType){
            case Comment: return "{0} commented on your post";
            case Message: return "{0} sent you a message";
            default: throw new Exception("Invalid notification type!");
        }
    }
}
