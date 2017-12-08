package finki.ukim.mk.soatickets.controllers;

import finki.ukim.mk.soatickets.business.services.implementation.MessageService;
import finki.ukim.mk.soatickets.business.view.models.messages.CreateMessageViewModel;
import finki.ukim.mk.soatickets.business.view.models.messages.MessageViewModel;
import finki.ukim.mk.soatickets.core.helpers.ErrorMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    @Autowired
    private MessageService service;

    @RequestMapping(value = "/sender/{id}", method = RequestMethod.GET)
    public List<MessageViewModel> getMessagesForSenderId(@PathVariable long senderId) {
        return service.getAllMessagesForSender(senderId);
    }

    @RequestMapping(value = "/reciver/{id}", method = RequestMethod.GET)
    public List<MessageViewModel> getMessagesForRecieverId(@PathVariable long recieverId) {
        return service.getAllMessagesForReciever(recieverId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Long deleteMessage(@PathVariable long messageId) throws Exception {
        return service.delete(messageId);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object createMessage(@Valid CreateMessageViewModel model, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors())
            return ErrorMessageHandler.ParseErrors(bindingResult.getFieldErrors());
        return service.create(model);
    }
}
