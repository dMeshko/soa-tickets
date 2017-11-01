package finki.ukim.mk.soatickets.business.services.implementation;

import finki.ukim.mk.soatickets.business.services.IUsersService;
import finki.ukim.mk.soatickets.business.view.models.user.RegisterUserViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UpdateUserViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DarkoM on 22.10.2017.
 */

@Service
public class UsersService implements IUsersService {
    @Autowired
    private IUserRepository userRepository;

    private ModelMapper modelMapper;

    public UsersService(){
        modelMapper = new ModelMapper();
    }

    @Override
    public List<UserViewModel> getAll() {
        List<UserViewModel> result = new ArrayList<>();
        for (User user : userRepository.findAll())
            result.add(modelMapper.map(user, UserViewModel.class));

        return result;
    }

    @Override
    public UserViewModel getById(Long userId) throws Exception {
        User dboUser = userRepository.findOne(userId);
        if (dboUser == null)
            throw new Exception("There is no such user!");

        return modelMapper.map(dboUser, UserViewModel.class);
    }

    @Override
    public Long register(RegisterUserViewModel user) throws Exception {
        if (userRepository.findByEmail(user.getEmail()) != null)
            throw new Exception("User already exists!");

        User dboUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getPhoneNumber());
        return userRepository.save(dboUser).getId();
    }

    @Override
    public Long update(UpdateUserViewModel user) {
        User dboUser = userRepository.findOne(user.getId());
        dboUser.setFirstName(user.getFirstName());
        dboUser.setLastName(user.getLastName());
        dboUser.setEmail(user.getEmail());
        dboUser.setPassword(user.getPassword());
        dboUser.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(dboUser);

        return dboUser.getId();
    }

    @Override
    public Long delete(Long userId) throws Exception {
        if (userId < 1)
            throw new Exception("The id has to be greater than 0!");

        userRepository.delete(userId);

        return userId;
    }

    @Override
    public UserViewModel findByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new Exception("There is no user associated with that email!");

        return modelMapper.map(user, UserViewModel.class);
    }
}