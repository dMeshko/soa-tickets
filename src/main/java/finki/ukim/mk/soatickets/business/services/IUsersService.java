package finki.ukim.mk.soatickets.business.services;

import finki.ukim.mk.soatickets.business.view.models.user.RegisterUserViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UpdateUserViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;

import java.util.List;

/**
 * Created by DarkoM on 22.10.2017.
 */
public interface IUsersService {
    List<UserViewModel> getAll();
    UserViewModel getById(Long userId) throws Exception;
    Long register(RegisterUserViewModel user) throws Exception;
    Long update(UpdateUserViewModel user);
    Long delete(Long userId) throws Exception;
    UserViewModel findByEmail(String email) throws Exception;
    UserViewModel findByUsername(String username) throws Exception;
}
