package finki.ukim.mk.soatickets.business.view.models.user;

import finki.ukim.mk.soatickets.business.view.models.LookupViewModel;

import java.util.Date;

/**
 * Created by DarkoM on 08.12.2017.
 */
public class NotificationViewModel {
    private String content;
    private LookupViewModel<Long> userFrom;
    private Date createdAt;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LookupViewModel<Long> getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(LookupViewModel<Long> userFrom) {
        this.userFrom = userFrom;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
