package finki.ukim.mk.soatickets.controllers;

import finki.ukim.mk.soatickets.business.services.implementation.TicketsService;
import finki.ukim.mk.soatickets.business.view.models.tickets.TicketViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
public class TicketController {

    @Autowired
    private TicketsService ticketsService;

    @RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
    public List<TicketViewModel> getTicketsForEvent(@PathVariable long id) throws Exception {
        return ticketsService.getAllForEvent(id);
    }

}
