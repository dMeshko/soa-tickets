package finki.ukim.mk.soatickets.business.services;

import finki.ukim.mk.soatickets.business.view.models.events.EventViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;

import java.util.List;

/**
 * Created by DarkoM on 01.12.2017.
 */

public interface ISearchService {
    List<EventViewModel> searchEvents(String searchTerm);
    List<UserViewModel> searchUsers(String searchTerm);
}
