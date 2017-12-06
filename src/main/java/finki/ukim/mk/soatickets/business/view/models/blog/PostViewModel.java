package finki.ukim.mk.soatickets.business.view.models.blog;

import java.util.Date;

public class PostViewModel {
    private Long id;
    private String title;
    private String rawContent;
    private Date createdAt;
    private Date publishedAt;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getRawContent() { return rawContent; }

    public void setRawContent(String rawContent) { this.rawContent = rawContent; }

    public Date getCreatedAt() { return createdAt; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getPublishedAt() { return publishedAt; }

    public void setPublishedAt(Date publishedAt) { this.publishedAt = publishedAt; }

}