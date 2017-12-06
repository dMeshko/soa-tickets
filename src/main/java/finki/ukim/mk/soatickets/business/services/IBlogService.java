package finki.ukim.mk.soatickets.business.services;

import finki.ukim.mk.soatickets.business.view.models.blog.PostViewModel;
import finki.ukim.mk.soatickets.models.user.User;

import java.util.List;

public interface IBlogService {

    List <PostViewModel> getPublishedPosts();
    List <PostViewModel> getPostsByAuthor(User author);
    List <PostViewModel> searchPosts(String query);
    long addPost(String title, String content, String username) throws Exception;
    long deletePost(Long postId) throws Exception;
    long addComment(String content, String username, Long postId) throws Exception;
    long deleteComment(Long commentId) throws Exception;

}
