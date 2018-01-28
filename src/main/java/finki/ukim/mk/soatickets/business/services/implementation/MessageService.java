package finki.ukim.mk.soatickets.business.services.implementation;

import finki.ukim.mk.soatickets.business.services.IMessageService;
import finki.ukim.mk.soatickets.business.services.INotificationService;
import finki.ukim.mk.soatickets.business.view.models.messages.CreateMessageViewModel;
import finki.ukim.mk.soatickets.business.view.models.messages.MessageViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.NotificationType;
import finki.ukim.mk.soatickets.models.messages.Message;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.IMessageRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService implements IMessageService {

    private ModelMapper mapper;

    public MessageService() { mapper = new ModelMapper(); }

    @Autowired
    private IMessageRepository messageRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private INotificationService notificationService;

    @Override
    public List<MessageViewModel> getAllMessagesForSender(long senderId) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        List<MessageViewModel> results = new ArrayList<>();
        List<Message> messagesResults = messageRepository.getAllBySenderId(senderId);
        for(Message msg : messagesResults) {
            MessageViewModel messageViewModel = mapper.map(msg, MessageViewModel.class);
            messageViewModel.setDate(dateFormat.format(msg.getDate()));
            results.add(messageViewModel);
        }

        return results;
    }

    @Override
    public List<MessageViewModel> getAllMessagesForReceiver(long receiverId) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        List<MessageViewModel> results = new ArrayList<>();
        List<Message> messagesResults = messageRepository.getAllByReceiverId(receiverId);
        for(Message msg : messagesResults) {
            MessageViewModel messageViewModel = mapper.map(msg, MessageViewModel.class);
            messageViewModel.setDate(dateFormat.format(msg.getDate()));
            results.add(messageViewModel);
        }

        return results;
    }

    @Override
    public Long create(CreateMessageViewModel message) throws Exception {
        User sender = userRepository.findOne(message.getSenderId());
        if(sender == null) {
            throw new Exception("Sender not found");
        }

        User receiver = userRepository.findOne(message.getReceiverId());
        if(receiver == null) {
            throw new Exception("Receiver not found");
        }

        Message messageNew = new Message(sender, receiver, message.getSubject(), message.getMessage());

        Message savedMessage = messageRepository.save(messageNew);

        notificationService.sendNotification(sender.getId(), receiver.getId(), NotificationType.Message);

        return savedMessage.getId();
    }

    @Override
    public Long delete(long messageId) {
        messageRepository.delete(messageId);
        return messageId;
    }
}
