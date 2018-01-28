package finki.ukim.mk.soatickets.business.view.models.blog;

import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by DarkoM on 08.12.2017.
 */
public class CreatePostViewModel {
    @NotEmpty
    @Size(min = 3)
    private String title;

    @NotEmpty
    @Size(min = 3)
    private String content;

    private UserViewModel author;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public UserViewModel getAuthor() {
        return author;
    }

    public void setAuthor(UserViewModel author) {
        this.author = author;
    }
}
