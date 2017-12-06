package finki.ukim.mk.soatickets.models.blog;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 500, nullable = false)
    private String content;

    @Column(nullable = false)
    private String username;

    /**Relationships**/
    @ManyToOne
    @JoinColumn(name = "comment_post_id")
    /**end relationships**/

    private Post post;
    private Date createdAt = new Date();

    public Comment() {
    }

    public Comment(String content, String username, Post post) {
        this.content = content;
        this.username = username;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
