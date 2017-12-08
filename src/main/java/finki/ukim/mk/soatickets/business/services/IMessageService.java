package finki.ukim.mk.soatickets.business.services;

import finki.ukim.mk.soatickets.business.view.models.messages.CreateMessageViewModel;
import finki.ukim.mk.soatickets.business.view.models.messages.MessageViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;

import java.util.List;

public interface IMessageService {
    List<MessageViewModel> getAllMessagesForSender(long senderId);
    List<MessageViewModel> getAllMessagesForReceiver(long receiverId);
    Long create(CreateMessageViewModel message) throws Exception;
}
