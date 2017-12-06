package finki.ukim.mk.soatickets.controllers;

import finki.ukim.mk.soatickets.business.services.IUsersService;
import finki.ukim.mk.soatickets.business.services.IBlogService;
import finki.ukim.mk.soatickets.business.view.models.blog.PostViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class BlogController {

    @Autowired
    private IBlogService blogService;
    @Autowired
    private IUsersService usersService;

    @RequestMapping(method = RequestMethod.GET)
    public List<PostViewModel> index(Model model) {

        List<PostViewModel> postPage = blogService.getPublishedPosts();
        return renderPosts(postPage, "/posts", model);
    }

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public List<PostViewModel> showUserPosts(@PathVariable("username") String username, Model model) {

        UserViewModel author = usersService.findByUsername(username);

        if (author == null) {
            throw new Exception;
        }

        model.addAttribute("well", "Posts by " + author.getFirstName() + author.getLastName());
        return renderPosts(blogService.getPostsByAuthor(author), "/user/" + username, model);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String showPost(@PathVariable("username") String username, Model model) {

        UserViewModel author = usersService.findByUsername(username);
        PostViewModel post = blogService.getPostsByAuthor(author);

        if (post == null) {
            throw new Exception;
        }

        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping(value = "/search")
    public List<PostViewModel> searchPosts(@RequestParam("q") String query, Model model) {

        if (query == null) {
//            return redirect("/posts");
        }

        model.addAttribute("well", "Search: " + query);
        return renderPosts(blogService.searchPosts(query), "/posts/search?q=" + query, model);
    }

    private List<PostViewModel> renderPosts(List<PostViewModel> page, String url, Model model) {

//        PageWrapper<Post> pageWrapper = new PageWrapper<>(page, url);
//        model.addAttribute("posts", page.getContent());
//        model.addAttribute("page", pageWrapper);
//        return "index";
        System.out.println(page);
        return blogService.getPublishedPosts();
    }


    @RequestMapping(
            method = RequestMethod.POST,
            value = "/{username}/comment/add"
    )
    public Long addComment(String comment, Principal principal, Long postId) {

        if (comment == null) {
            return blogService.addComment("this is a comment", principal.toString(), postId);
        }

        return blogService.addComment(comment, principal.toString(), postId);
    }
}
