package finki.ukim.mk.soatickets.controllers;

import finki.ukim.mk.soatickets.business.services.IBlogService;
import finki.ukim.mk.soatickets.business.services.ISearchService;
import finki.ukim.mk.soatickets.business.services.IUsersService;
import finki.ukim.mk.soatickets.business.view.models.blog.CreatePostViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.RegisterUserViewModel;
import finki.ukim.mk.soatickets.models.blog.Comment;
import finki.ukim.mk.soatickets.models.blog.Post;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.ICommentRepository;
import finki.ukim.mk.soatickets.repositories.IPostRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class BlogControllerTest {


    @InjectMocks
    private BlogController mockBlogController;

    private MockMvc mockMvc;

    @Mock
    IBlogService blogService;

    @Mock
    IUsersService usersService;

    @Mock
    IPostRepository postRepository;

    @Mock
    IUserRepository userRepository;

    @Mock
    ICommentRepository commentRepository;

    @Before
    public void setup() {
        // Given
        // Create one author (user)
        User user = new User();
        user.setFirstName("FIRST NAME");
        user.setLastName("LAST NAME");
        user.setEmail("EMAIL");
        user.setPhoneNumber("023354235");
        user.setPassword("password");
        user.setActive(true);
        user.setId(Long.valueOf(2));

        // create one post
        Post post = new Post("TITLE", "CONTENT",user);
        post.setId(Long.valueOf(1));
        // create one comment
        Comment comment = new Comment("content",user,post);
        comment.setId(Long.valueOf(3));
        List comments = new ArrayList(2);
        comments.add(comment);

        post.setComments(comments);

        List postList = new ArrayList(2);
        postList.add(post);
        user.setPosts(postList);

        when(postRepository.findAll()).thenReturn(postList);
        when(postRepository.findOne(Long.valueOf(1))).thenReturn(post);
        when(userRepository.findOne(Long.valueOf(2))).thenReturn(user);
        when(commentRepository.findOne(Long.valueOf(3))).thenReturn(comment);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(postRepository.save(any(Post.class))).thenReturn(post);
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        mockMvc = MockMvcBuilders.standaloneSetup(mockBlogController).build();

    }

    @Test
    public void shouldReturnAllPosts() throws Exception {
        mockMvc.perform(get("/api/blog/")).andExpect(status().isOk());
        verify(blogService, times(1)).getAllPosts();
    }

    @Test
    public void shouldReturnAllCommentsForPost() throws Exception {
        MvcResult results = mockMvc.perform(get("/api/blog/{id}/comment", 1)).andExpect(status().isOk()).andReturn();
        verify(blogService, times(1)).getAllCommentsForPost(1);
    }

    @Test
    public void shouldDeletePost() throws Exception {
        mockMvc.perform(delete("/api/blog/{id}", 1)).andExpect(status().isOk());
        verify(blogService, times(1)).deletePost(Long.valueOf(1));
    }

    @Test
    public void shouldNotAllowDeleteComment() throws Exception {
        mockMvc.perform(delete("/api/blog/{id]/comment", 1)).andExpect(status().is4xxClientError());
        verify(blogService, times(0)).deleteComment(Long.valueOf(1));
    }

    @Test
    public void shouldCreatePost() throws Exception {
        CreatePostViewModel createPostViewModel = new CreatePostViewModel();
        String json = new JSONObject().put("title", "Test")
                                      .put("content", "text for test")
                                      .put("author", "1").toString();

        mockMvc.perform(post("/api/blog/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
        verify(blogService, times(1)).addPost(createPostViewModel);
    }

    @Test
    public void shouldGetAllPoststForComment() throws Exception {
        mockMvc.perform(get("/api/blog/{id}/comment", 1)).andExpect(status().isOk());
        verify(blogService, times(0)).getAllCommentsForPost(Long.valueOf(1));
    }
}
