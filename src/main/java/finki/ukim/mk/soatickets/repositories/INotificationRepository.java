package finki.ukim.mk.soatickets.repositories;

import finki.ukim.mk.soatickets.models.user.Notification;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by DarkoM on 08.12.2017.
 */
public interface INotificationRepository extends CrudRepository<Notification, Long> {

}