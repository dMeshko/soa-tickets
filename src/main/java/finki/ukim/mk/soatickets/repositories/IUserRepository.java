package finki.ukim.mk.soatickets.repositories;

import finki.ukim.mk.soatickets.models.notifications.Notification;
import finki.ukim.mk.soatickets.models.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by DarkoM on 22.10.2017.
 */
public interface IUserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}