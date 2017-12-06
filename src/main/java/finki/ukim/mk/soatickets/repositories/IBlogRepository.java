package finki.ukim.mk.soatickets.repositories;

import finki.ukim.mk.soatickets.models.blog.Post;
import finki.ukim.mk.soatickets.models.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IBlogRepository extends CrudRepository<Post, Long> {

    Post findByPostId(Long id);
    List<Post> findAllOrderByPublishedAtDesc();
    List<Post> findByAuthorOrderByPublishedAtDesc(User author);
    List<Post> findByTitleIgnoreCaseOrderByPublishedAtDesc(String query);

}