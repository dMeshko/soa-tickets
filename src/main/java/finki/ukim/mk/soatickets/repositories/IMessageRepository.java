package finki.ukim.mk.soatickets.repositories;

import finki.ukim.mk.soatickets.models.messages.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IMessageRepository extends CrudRepository<Message, Long> {
    public List<Message> getAllBySenderId(long senderId);
    public List<Message> getAllByRecieverId(long recieverId);
}
