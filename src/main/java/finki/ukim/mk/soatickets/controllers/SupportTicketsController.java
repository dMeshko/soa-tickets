package finki.ukim.mk.soatickets.controllers;

import finki.ukim.mk.soatickets.business.services.ISupportTicketService;
import finki.ukim.mk.soatickets.business.services.IUsersService;
import finki.ukim.mk.soatickets.business.view.models.support.SupportTicketViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.RegisterUserViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;
import finki.ukim.mk.soatickets.core.helpers.ErrorMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by DarkoM on 09.12.2017.
 */

@RestController
@RequestMapping(value = "/api/support", produces = MediaType.APPLICATION_JSON_VALUE)
public class SupportTicketsController {
    @Autowired
    private ISupportTicketService supportTicketService;

    @Autowired
    private IUsersService usersService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('admin')")
    public List<SupportTicketViewModel> getAllSupportTickets() {
        return supportTicketService.getAllSupportTickets();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('user')")
    public long register(@RequestParam String content, Principal principal) throws Exception {
        UserViewModel currentUser = usersService.findByEmail(principal.getName());

        return supportTicketService.createSupportTicket(currentUser.getId(), content);
    }
}
