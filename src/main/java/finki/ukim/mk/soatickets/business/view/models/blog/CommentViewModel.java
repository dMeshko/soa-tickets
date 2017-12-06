package finki.ukim.mk.soatickets.business.view.models.blog;

import java.util.Date;

public class CommentViewModel {
    private Long id;
    private String content;
    private String username;
    private Date createdAt;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public Date getCreatedAt() { return createdAt; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
