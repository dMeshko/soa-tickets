package finki.ukim.mk.soatickets.controllers;

import finki.ukim.mk.soatickets.business.services.IUsersService;
import finki.ukim.mk.soatickets.business.services.implementation.MessageService;
import finki.ukim.mk.soatickets.business.view.models.messages.CreateMessageViewModel;
import finki.ukim.mk.soatickets.business.view.models.messages.MessageViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;
import finki.ukim.mk.soatickets.core.helpers.ErrorMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {
    @Autowired
    private IUsersService usersService;

    @Autowired
    private MessageService service;

    @RequestMapping(value = "/sender/{senderId}", method = RequestMethod.GET)
    public List<MessageViewModel> getMessagesForSender(@PathVariable long senderId) {
        return service.getAllMessagesForSender(senderId);
    }

    @RequestMapping(value = "/receiver/{receiverId}", method = RequestMethod.GET)
    public List<MessageViewModel> getMessagesForReceiver(@PathVariable long receiverId) {
        return service.getAllMessagesForReceiver(receiverId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Long deleteMessage(@PathVariable long messageId) throws Exception {
        return service.delete(messageId);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object createMessage(@Valid CreateMessageViewModel model, BindingResult bindingResult, Principal principal) throws Exception {
        if (bindingResult.hasErrors())
            return ErrorMessageHandler.ParseErrors(bindingResult.getFieldErrors());

        UserViewModel currentUser = usersService.findByEmail(principal.getName());
        model.setSenderId(currentUser.getId());

        return service.create(model);
    }
}
