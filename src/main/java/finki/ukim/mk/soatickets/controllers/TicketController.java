package finki.ukim.mk.soatickets.controllers;

import finki.ukim.mk.soatickets.business.services.IUsersService;
import finki.ukim.mk.soatickets.business.services.implementation.TicketsService;
import finki.ukim.mk.soatickets.business.view.models.events.EventViewModel;
import finki.ukim.mk.soatickets.business.view.models.tickets.PurchaseTicketViewModel;
import finki.ukim.mk.soatickets.business.view.models.tickets.TicketViewModel;
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

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
public class TicketController {
    @Autowired
    private IUsersService usersService;

    @Autowired
    private TicketsService ticketsService;

    @RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
    public List<TicketViewModel> getTicketsForEvent(@PathVariable long id) throws Exception {
        return ticketsService.getAllForEvent(id);
    }

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('user')")
    public Object purchaseTicket(Principal principal, PurchaseTicketViewModel purchaseTicketViewModel, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors())
            return ErrorMessageHandler.ParseErrors(bindingResult.getFieldErrors());

        UserViewModel currentUser;
        if (purchaseTicketViewModel.getUserId() == 0)
        {
            currentUser = usersService.findByEmail(principal.getName());
            purchaseTicketViewModel.setUserId(currentUser.getId());
        }

        return ticketsService.purchaseTicket(purchaseTicketViewModel);
    }
}
