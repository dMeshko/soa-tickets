package finki.ukim.mk.soatickets.repositories;

import finki.ukim.mk.soatickets.models.user.Role;
import finki.ukim.mk.soatickets.models.user.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by DarkoM on 03.12.2017.
 */
public interface IRoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
