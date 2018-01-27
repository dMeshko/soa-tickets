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
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
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
    List<User> userList = new ArrayList<>();
    userList.add(user);
    when(userRepository.findAll()).thenReturn(userList);
    when(userRepository.findOne(Long.valueOf(1))).thenReturn(user);
    when(userRepository.save(user)).thenReturn(user);
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
    // Given
    RegisterUserViewModel user = new RegisterUserViewModel();
    user.setFirstName("FIRST NAME");
    user.setLastName("LAST NAME");
    user.setEmail("popovam@outlook.com");
    user.setPhoneNumber("023354235");
    user.setPassword("password");
    // When
    Long userId = usersService.register(user);

    // Then
    Assert.assertThat(userId, Matchers.greaterThan(Long.valueOf(0)));
  }








}
