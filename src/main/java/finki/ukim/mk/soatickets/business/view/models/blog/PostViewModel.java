package finki.ukim.mk.soatickets.business.view.models.blog;

import finki.ukim.mk.soatickets.business.view.models.LookupViewModel;

import java.util.Date;

/**
 * Created by DarkoM on 08.12.2017.
 */
public class PostViewModel {
    private String title;
    private String content;
    private LookupViewModel<Long> author;
    private Date createdAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LookupViewModel<Long> getAuthor() {
        return author;
    }

    public void setAuthor(LookupViewModel<Long> author) {
        this.author = author;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
