package finki.ukim.mk.soatickets.business.view.models.support;

import finki.ukim.mk.soatickets.business.view.models.LookupViewModel;

import java.util.Date;

/**
 * Created by DarkoM on 09.12.2017.
 */
public class SupportTicketViewModel {
    private long id;
    private String content;
    private String createdOn;
    private LookupViewModel<Long> user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public LookupViewModel<Long> getUser() {
        return user;
    }

    public void setUser(LookupViewModel<Long> user) {
        this.user = user;
    }
}
