package finki.ukim.mk.soatickets.business.view.models.blog;

import finki.ukim.mk.soatickets.business.view.models.LookupViewModel;

import java.util.Date;

/**
 * Created by DarkoM on 08.12.2017.
 */
public class CommentViewModel {
    private String content;
    private LookupViewModel<Long> author;
    private LookupViewModel<Long> post;
    private String createdAt;

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

    public LookupViewModel<Long> getPost() {
        return post;
    }

    public void setPost(LookupViewModel<Long> post) {
        this.post = post;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
