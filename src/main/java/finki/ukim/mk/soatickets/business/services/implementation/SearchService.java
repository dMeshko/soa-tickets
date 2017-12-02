package finki.ukim.mk.soatickets.business.services.implementation;

import finki.ukim.mk.soatickets.business.services.ISearchService;
import finki.ukim.mk.soatickets.business.view.models.events.EventViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;
import finki.ukim.mk.soatickets.models.events.Event;
import finki.ukim.mk.soatickets.models.user.User;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.apache.lucene.search.Query;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DarkoM on 01.12.2017.
 */

@Service
public class SearchService implements ISearchService {
    @Autowired
    private final EntityManager entityManager;

    private ModelMapper modelMapper;

    @Autowired
    public SearchService(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
        this.modelMapper = new ModelMapper();
    }

    // build the lucine index on start of the app.
    public void initialize(){
        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EventViewModel> searchEvents(String searchTerm) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Event.class).get();
        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(2).withPrefixLength(1).onFields("name", "description", "location")
                .matching(searchTerm).createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Event.class);

        // execute search

        List<Event> eventList = null;
        List<EventViewModel> mappedEventList = new ArrayList<>();
        try {
            eventList = jpaQuery.getResultList();
            for (Event event : eventList){
                mappedEventList.add(modelMapper.map(event, EventViewModel.class));
            }
        } catch (NoResultException nre) {
            ;// do nothing
            throw nre;
        }

        return mappedEventList;
    }

    @Override
    public List<UserViewModel> searchUsers(String searchTerm) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(User.class).get();
        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(2).withPrefixLength(1).onFields("firstName", "lastName", "email", "phoneNumber", "ownedEvents.name")
                .matching(searchTerm).createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, User.class);

        // execute search

        List<User> usersList = null;
        List<UserViewModel> mappedUsersList = new ArrayList<>();
        try {
            usersList = jpaQuery.getResultList();
            for (User user : usersList){
                mappedUsersList.add(modelMapper.map(user, UserViewModel.class));
            }
        } catch (NoResultException nre) {
            ;// do nothing
            throw nre;
        }

        return mappedUsersList;
    }
}
