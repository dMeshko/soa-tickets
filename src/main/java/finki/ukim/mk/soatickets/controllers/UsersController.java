package finki.ukim.mk.soatickets.controllers;

import finki.ukim.mk.soatickets.business.services.INotificationService;
import finki.ukim.mk.soatickets.business.services.ISearchService;
import finki.ukim.mk.soatickets.business.services.IUsersService;
import finki.ukim.mk.soatickets.business.view.models.user.NotificationViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.RegisterUserViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UpdateUserViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;
import finki.ukim.mk.soatickets.core.helpers.ErrorMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by DarkoM on 22.10.2017.
 */

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {
    @Autowired
    private IUsersService usersService;

    @Autowired
    private ISearchService searchService;

    @Autowired
    private INotificationService notificationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('admin')")
    public List<UserViewModel> getAllUsers(Principal principal) {
        System.out.println(principal.toString());
        return usersService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserViewModel getUser(@PathVariable Long id) throws Exception {
        return usersService.getById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object register(@Valid RegisterUserViewModel user, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors())
            return ErrorMessageHandler.ParseErrors(bindingResult.getFieldErrors());

        return usersService.register(user);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Object update(@Valid UpdateUserViewModel user, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return ErrorMessageHandler.ParseErrors(bindingResult.getFieldErrors());

        return usersService.update(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Long delete(@PathVariable Long id) throws Exception {
        return usersService.delete(id);
    }

    @RequestMapping(value = "/email/{email:.+}", method = RequestMethod.GET)
    public UserViewModel getUserByEmail(@PathVariable String email) throws Exception {
        return usersService.findByEmail(email);
    }

    @RequestMapping(value = "/search/{term}", method = RequestMethod.GET)
    public List<UserViewModel> search(@PathVariable String term) throws Exception {
        return searchService.searchUsers(term);
    }

    @RequestMapping(value = "/notifications", method = RequestMethod.GET)
    public List<NotificationViewModel> getUserNotifications(Principal principal) throws Exception {
        UserViewModel currentUser = usersService.findByEmail(principal.getName());
        return notificationService.getAllForUser(currentUser.getId());
    }
}