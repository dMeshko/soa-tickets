package finki.ukim.mk.soatickets.business.services.implementation;

import finki.ukim.mk.soatickets.business.services.INotificationService;
import finki.ukim.mk.soatickets.business.view.models.notifications.NotificationViewModel;
import finki.ukim.mk.soatickets.models.notifications.Notification;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.INotificationRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;

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
    public List<NotificationViewModel> getUserNotifications(Long userId) throws Exception {
        User user = userRepository.findOne(userId);
        if (user == null)
            throw new Exception("User not found!");

        List<NotificationViewModel> result = new ArrayList<>();
        List<Notification> dboNotifications = userRepository.getNotifications();
        for(Notification notification : dboNotifications) {
            NotificationViewModel viewModel = modelMapper.map(notification, NotificationViewModel.class);
            result.add(viewModel);
        }
        return result;
    }
}
