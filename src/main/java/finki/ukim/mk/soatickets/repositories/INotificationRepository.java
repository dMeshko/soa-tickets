package finki.ukim.mk.soatickets.repositories;

import finki.ukim.mk.soatickets.models.notifications.Notification;
import org.springframework.data.repository.CrudRepository;

public interface INotificationRepository extends CrudRepository<Notification, Long> {

}