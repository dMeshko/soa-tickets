package finki.ukim.mk.soatickets.business.services.implementation;

import finki.ukim.mk.soatickets.business.services.IMessageService;
import finki.ukim.mk.soatickets.business.view.models.messages.CreateMessageViewModel;
import finki.ukim.mk.soatickets.business.view.models.messages.MessageViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;
import finki.ukim.mk.soatickets.models.messages.Message;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.IMessageRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.util.resources.cldr.sg.CurrencyNames_sg;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService implements IMessageService {

    private ModelMapper mapper;

    public MessageService() { mapper = new ModelMapper(); }

    @Autowired
    private IMessageRepository repository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<MessageViewModel> getAllMessagesForSender(long senderId) {
        List<MessageViewModel> results = new ArrayList<>();
        List<Message> messagesResults = repository.getAllBySenderId(senderId);
        for(Message msg : messagesResults) {
            MessageViewModel messageViewModel = mapper.map(msg, MessageViewModel.class);
            results.add(messageViewModel);
        }

        return results;
    }

    @Override
    public List<MessageViewModel> getAllMessagesForReciever(long recieverId) {
        List<MessageViewModel> results = new ArrayList<>();
        List<Message> messagesResults = repository.getAllBySenderId(recieverId);
        for(Message msg : messagesResults) {
            MessageViewModel messageViewModel = mapper.map(msg, MessageViewModel.class);
            results.add(messageViewModel);
        }

        return results;
    }

    @Override
    public Long create(CreateMessageViewModel message) throws Exception {
        User sender = userRepository.findOne(message.getSenderId());
        User reciever = userRepository.findOne(message.getRecieverId());

        if(sender == null) {
            throw new Exception("Sender not found");
        }

        if(reciever == null) {
            throw new Exception("Reciever not found");
        }

        Message messageNew = new Message(sender,
                                         reciever,
                                         message.getSubject(),
                                         message.getMessage());

        repository.save(messageNew);

        return messageNew.getId();
    }

    public Long delete(long messageId) throws Exception {
        Message messageItem = repository.findOne(messageId);
        repository.delete(messageId);

        return messageItem.getId();
    }
}
