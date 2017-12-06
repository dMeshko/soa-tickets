package finki.ukim.mk.soatickets.models.blog;
import org.hibernate.annotations.Type;
import finki.ukim.mk.soatickets.models.user.User;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Column(nullable = false)
    @Type(type = "text")
    private String rawContent;


    /**Relationships**/
    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    private User author;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comment> comments;
    /**end relationships**/


    @Column(nullable = false)
    private Date createdAt = new Date();

    private Date publishedAt;

    public Post() {
    }

    public Post(String title, String rawContent, User author) {
        this.title = title;
        this.rawContent = rawContent;
        this.author = author;
    }

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

    public String getRawContent() {
        return rawContent;
    }

    public void setRawContent(String rawContent) {
        this.rawContent = rawContent;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
