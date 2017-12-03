package finki.ukim.mk.soatickets.controllers;

import finki.ukim.mk.soatickets.business.services.implementation.BoughtTicketService;
import finki.ukim.mk.soatickets.business.view.models.tickets.BoughtTicketViewModel;
import finki.ukim.mk.soatickets.business.view.models.tickets.TicketViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tickets/bought", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoughTicketsController {

    @Autowired
    private BoughtTicketService service;

    @RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
    private List<BoughtTicketViewModel> getBoughtTicketsForEvent(@PathVariable long id) throws Exception {
        return service.getAllForEvent(id);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    private List<BoughtTicketViewModel> getBoughtTicketsForUser(@PathVariable long id) throws Exception {
        return service.getAllForUser(id);
    }

}
