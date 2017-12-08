package finki.ukim.mk.soatickets.controllers;

import finki.ukim.mk.soatickets.business.services.IBlogService;
import finki.ukim.mk.soatickets.business.services.ISearchService;
import finki.ukim.mk.soatickets.business.services.IUsersService;
import finki.ukim.mk.soatickets.business.view.models.blog.AddCommentViewModel;
import finki.ukim.mk.soatickets.business.view.models.blog.CommentViewModel;
import finki.ukim.mk.soatickets.business.view.models.blog.CreatePostViewModel;
import finki.ukim.mk.soatickets.business.view.models.blog.PostViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.RegisterUserViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;
import finki.ukim.mk.soatickets.core.helpers.ErrorMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by DarkoM on 08.12.2017.
 */

@RestController
@RequestMapping(value = "/api/blog", produces = MediaType.APPLICATION_JSON_VALUE)
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @Autowired
    private IUsersService usersService;

    @Autowired
    private ISearchService searchService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<PostViewModel> getAllPosts() {
        return blogService.getAllPosts();
    }

    @RequestMapping(value = "/mine", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('user')")
    public List<PostViewModel> getMinePosts(Principal principal) throws Exception {
        UserViewModel currentUser = usersService.findByEmail(principal.getName());
        return blogService.getPostsByAuthor(currentUser);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object addPost(@Valid CreatePostViewModel post, BindingResult bindingResult, Principal principal) throws Exception {
        if (bindingResult.hasErrors())
            return ErrorMessageHandler.ParseErrors(bindingResult.getFieldErrors());

        UserViewModel currentUser = usersService.findByEmail(principal.getName());
        post.setAuthor(currentUser);

        return blogService.addPost(post);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Long deletePost(@PathVariable Long id) throws Exception {
        return blogService.deletePost(id);
    }

    @RequestMapping(value = "/{postId}/comment", method = RequestMethod.POST)
    public Object addComment(@PathVariable Long postId, @Valid AddCommentViewModel comment, BindingResult bindingResult, Principal principal) throws Exception {
        if (bindingResult.hasErrors())
            return ErrorMessageHandler.ParseErrors(bindingResult.getFieldErrors());

        comment.setPostId(postId);

        UserViewModel currentUser = usersService.findByEmail(principal.getName());
        comment.setAuthor(currentUser);

        return blogService.addComment(comment);
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.DELETE)
    public Long deleteComment(@PathVariable Long id) throws Exception {
        return blogService.deleteComment(id);
    }

    @RequestMapping(value = "/{postId}/comment", method = RequestMethod.GET)
    public List<CommentViewModel> getAllPosts(@PathVariable Long postId) throws Exception {
        return blogService.getAllCommentsForPost(postId);
    }
}
