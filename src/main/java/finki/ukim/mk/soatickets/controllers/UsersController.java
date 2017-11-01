package finki.ukim.mk.soatickets.controllers;

import finki.ukim.mk.soatickets.business.services.IUsersService;
import finki.ukim.mk.soatickets.business.view.models.user.RegisterUserViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UpdateUserViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;
import finki.ukim.mk.soatickets.controllers.helpers.ErrorMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by DarkoM on 22.10.2017.
 */

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {
    @Autowired
    private IUsersService usersService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<UserViewModel> getAllUsers() {
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

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public Long delete(@PathParam("id") Long id) throws Exception {
        return usersService.delete(id);
    }

    @RequestMapping(value = "/email/{email:.+}", method = RequestMethod.GET)
    public UserViewModel getUserByEmail(@PathVariable String email) throws Exception {
        return usersService.findByEmail(email);
    }
}