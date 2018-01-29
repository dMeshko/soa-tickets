package finki.ukim.mk.soatickets.business.services.implementation;

import finki.ukim.mk.soatickets.business.services.ISupportTicketService;
import finki.ukim.mk.soatickets.business.view.models.LookupViewModel;
import finki.ukim.mk.soatickets.business.view.models.support.SupportTicketViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;
import finki.ukim.mk.soatickets.models.support.SupportTicket;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.ISupportTicketRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DarkoM on 09.12.2017.
 */

@Service
public class SupportTicketService implements ISupportTicketService {
    @Autowired
    private ISupportTicketRepository supportTicketRepository;

    @Autowired
    private IUserRepository userRepository;

    private ModelMapper modelMapper;

    public SupportTicketService(){
        modelMapper = new ModelMapper();
    }

    @Override
    public long createSupportTicket(long userId, String content) throws Exception {
        if (content.trim().length() == 0)
            throw new Exception("Please provide more details about the issue You're trying to report.");

        User user = userRepository.findOne(userId);

        if (user == null)
            throw new Exception("User not found!");

        SupportTicket supportTicket = new SupportTicket(content, user);
        SupportTicket savedTicket = supportTicketRepository.save(supportTicket);

        return savedTicket.getId();
    }

    @Override
    public List<SupportTicketViewModel> getAllSupportTickets() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        List<SupportTicketViewModel> result = new ArrayList<>();
        for (SupportTicket supportTicket : supportTicketRepository.findAll())
        {
            SupportTicketViewModel supportTicketViewModel = modelMapper.map(supportTicket, SupportTicketViewModel.class);
            LookupViewModel<Long> userViewModel = new LookupViewModel<>(supportTicket.getUser().getId(), supportTicket.getUser().toString());
            supportTicketViewModel.setUser(userViewModel);
            supportTicketViewModel.setCreatedOn(dateFormat.format(supportTicket.getCreatedOn()));
            result.add(supportTicketViewModel);
        }

        return result;
    }
}
