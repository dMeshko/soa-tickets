package finki.ukim.mk.soatickets.repositories;

import finki.ukim.mk.soatickets.models.user.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by DarkoM on 22.10.2017.
 */
public interface IUserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}