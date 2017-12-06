package finki.ukim.mk.soatickets.repositories;

import finki.ukim.mk.soatickets.models.blog.Comment;
import org.springframework.data.repository.CrudRepository;

public interface ICommentRepository extends CrudRepository<Comment, Long> {

}