package finki.ukim.mk.soatickets.business.services;

import finki.ukim.mk.soatickets.business.view.models.blog.AddCommentViewModel;
import finki.ukim.mk.soatickets.business.view.models.blog.CommentViewModel;
import finki.ukim.mk.soatickets.business.view.models.blog.CreatePostViewModel;
import finki.ukim.mk.soatickets.business.view.models.blog.PostViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;

import java.util.List;

/**
 * Created by DarkoM on 08.12.2017.
 */
public interface IBlogService {
    List<PostViewModel> getAllPosts();
    List <PostViewModel> getPostsByAuthor(UserViewModel author) throws Exception;
    long addPost(CreatePostViewModel postViewModel) throws Exception;
    long deletePost(Long postId) throws Exception;
    long addComment(AddCommentViewModel commentViewModel) throws Exception;
    long deleteComment(Long commentId) throws Exception;
    List<CommentViewModel> getAllCommentsForPost(long postId) throws Exception;
}
