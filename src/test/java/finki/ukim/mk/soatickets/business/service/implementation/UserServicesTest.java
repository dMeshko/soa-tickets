package finki.ukim.mk.soatickets.business.service.implementation;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import finki.ukim.mk.soatickets.business.services.IUsersService;
import finki.ukim.mk.soatickets.business.services.implementation.UsersService;
import finki.ukim.mk.soatickets.business.view.models.user.RegisterUserViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.when;
import finki.ukim.mk.soatickets.repositories.IRoleRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;

/**
 * Unit tests for {@link UsersService}
 */
@RunWith(JUnit4.class)
public class UserServicesTest {

  @Spy
  private IUserRepository userRepository;

  @Mock
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Mock
  private IRoleRepository roleRepository;

  @InjectMocks
  private IUsersService usersService = new UsersService();

  @Test
  public void shouldGetAllUsers() throws Exception {
    // Given
    UserViewModel viewModel = new UserViewModel();
    viewModel.setFirstName("FIRST NAME");
    viewModel.setLastName("LAST NAME");
    viewModel.setEmail("EMAIL");
    viewModel.setPhoneNumber("023354235");

    List<UserViewModel> viewModelList = new ArrayList<>(2);
    viewModelList.add(viewModel);

   when(userRepository.findAll()).thenReturn(Mockito.anyList());
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
    Assert.assertThat(userViewModelEmail, Matchers.equalTo("023354235"));


  }

}
