package finki.ukim.mk.soatickets.business.service.implementation;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import finki.ukim.mk.soatickets.business.services.IUsersService;
import finki.ukim.mk.soatickets.business.services.implementation.UsersService;

import finki.ukim.mk.soatickets.business.view.models.user.RegisterUserViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UpdateUserViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;


import static org.mockito.Mockito.when;

import finki.ukim.mk.soatickets.models.user.Role;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.IRoleRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;

/**
 * Unit tests for {@link UsersService}
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServicesTest {

  @Mock
  private IUserRepository userRepository;

  @Mock
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Mock
  private IRoleRepository roleRepository;

  @InjectMocks
  private IUsersService usersService = new UsersService();

  @Before
  public void setup() {
    // Given
    User user = new User();
    user.setFirstName("FIRST NAME");
    user.setLastName("LAST NAME");
    user.setEmail("EMAIL");
    user.setPhoneNumber("023354235");
    user.setPassword("password");
    user.setActive(true);
    user.setId(Long.valueOf(1));
    List<User> userList = new ArrayList<>();
    userList.add(user);
    when(userRepository.findAll()).thenReturn(userList);
    when(userRepository.findOne(Long.valueOf(1))).thenReturn(user);

    User newUser = new User();
    newUser.setFirstName("FIRST NAME");
    newUser.setLastName("LAST NAME");
    newUser.setEmail("popovam@outlook.com");
    newUser.setPhoneNumber("023354235");
    newUser.setPassword("password");
    newUser.setActive(true);
    newUser.setId(Long.valueOf(1));
    when(userRepository.save(newUser)).thenReturn(user);
    when(userRepository.findByEmail("EMAIL")).thenReturn(user);

    Role standardUserRole = new Role("user");
    standardUserRole.setUsers(userList);
    when(roleRepository.findByName("user")).thenReturn(standardUserRole);
    when(bCryptPasswordEncoder.encode("password" )).thenReturn("password");
  }

  @Test
  public void shouldGetAllUsers() {
    // when
    List<UserViewModel> usersServiceAll = usersService.getAll();
    // then
    UserViewModel userViewModel = usersServiceAll.get(0);

    String firstName = userViewModel.getFirstName();
    Assert.assertThat(firstName, Matchers.equalTo("FIRST NAME"));

    String lastName = userViewModel.getLastName();
    Assert.assertThat(lastName, Matchers.equalTo("LAST NAME"));

    String phoneNumber = userViewModel.getPhoneNumber();
    Assert.assertThat(phoneNumber, Matchers.equalTo("023354235"));

    String userViewModelEmail = userViewModel.getEmail();
    Assert.assertThat(userViewModelEmail, Matchers.equalTo("EMAIL"));
  }

  @Test
  public void shouldGetUserById() throws Exception {

    // When
    UserViewModel userViewModel = usersService.getById(Long.valueOf(1));

    // Then
    String firstName = userViewModel.getFirstName();
    Assert.assertThat(firstName, Matchers.equalTo("FIRST NAME"));

    String lastName = userViewModel.getLastName();
    Assert.assertThat(lastName, Matchers.equalTo("LAST NAME"));

    String phoneNumber = userViewModel.getPhoneNumber();
    Assert.assertThat(phoneNumber, Matchers.equalTo("023354235"));

    String userViewModelEmail = userViewModel.getEmail();
    Assert.assertThat(userViewModelEmail, Matchers.equalTo("EMAIL"));
  }

  @Test(expected = Exception.class)
  public void shouldGetExceptionWhenUserIsNotFound() throws Exception {
    UserViewModel userViewModel = null;
    // When
    try {
      userViewModel = usersService.getById(Long.valueOf(2));
    }
    finally {
      // Then
      Assert.assertThat(userViewModel, Matchers.nullValue());
    }
  }

  @Test
  public void shouldRegisterUser() throws  Exception {

    // Given
    RegisterUserViewModel user = new RegisterUserViewModel();
    user.setFirstName("FIRST NAME");
    user.setLastName("LAST NAME");
    user.setEmail("popovam@outlook.com");
    user.setPhoneNumber("023354235");
    user.setPassword("password");
    user.setId(Long.valueOf(1));

    // When
    Long userId = usersService.register(user);

    // Then
    Assert.assertThat(userId, Matchers.greaterThan(Long.valueOf(0)));
  }

  @Test(expected = Exception.class)
  public void shouldGetExceptionWhenEmailIsAlreadyRegistered() throws Exception {

    // Given
    RegisterUserViewModel user = new RegisterUserViewModel();
    user.setFirstName("FIRST NAME");
    user.setLastName("LAST NAME");
    user.setEmail("EMAIL");
    user.setPhoneNumber("023354235");
    user.setPassword("password");
    Long register = null;
    // When
    try {
      register = usersService.register(user);
    }
    finally {
      // Then
      Assert.assertThat(register, Matchers.nullValue());
    }
  }

  @Test
  public void shouldUpdateTheUser(){
    // given
    UpdateUserViewModel user = new UpdateUserViewModel();
    user.setId(Long.valueOf(1));
    user.setEmail("marija.popova@netcetera.com");
    user.setFirstName("Marija");
    user.setLastName("Popova");
    user.setPassword("password");
    user.setPhoneNumber("phonenumber");

    // when
    Long userId = usersService.update(user);

    // Then
    Assert.assertThat(userId, Matchers.greaterThan(Long.valueOf(0)));
  }

}
