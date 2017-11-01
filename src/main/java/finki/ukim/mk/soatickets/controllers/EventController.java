package finki.ukim.mk.soatickets.controllers;


import finki.ukim.mk.soatickets.business.services.IEventService;
import finki.ukim.mk.soatickets.business.view.models.events.CreateEventViewModel;
import finki.ukim.mk.soatickets.business.view.models.events.EventViewModel;
import finki.ukim.mk.soatickets.business.view.models.events.UpdateEventViewModel;
import finki.ukim.mk.soatickets.controllers.helpers.ErrorMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/events", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController {

    @Autowired
    private IEventService eventService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<EventViewModel> getAllEvents() {
        return eventService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EventViewModel getEvent(@PathVariable Long id) throws Exception {
        return eventService.getById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object create(@Valid CreateEventViewModel event, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors())
            return ErrorMessageHandler.ParseErrors(bindingResult.getFieldErrors());
        return eventService.create(event);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(@Valid UpdateEventViewModel event, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors())
            return ErrorMessageHandler.ParseErrors(bindingResult.getFieldErrors());
        return eventService.update(event);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Long delete(@PathVariable Long id) throws Exception {
        return eventService.delete(id);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public List<EventViewModel> getByName(@PathVariable String name) throws Exception {
        return eventService.findAllByName(name);
    }

    @RequestMapping(value = "/location/{location}", method = RequestMethod.GET)
    public List<EventViewModel> getByLocation(@PathVariable String location) throws Exception {
        return eventService.findAllByLocation(location);
    }

    @RequestMapping(value = "/date/{date}", method = RequestMethod.GET)
    public List<EventViewModel> byName(@PathVariable String date) throws Exception {
        return eventService.findAllByDate(date);
    }

}
