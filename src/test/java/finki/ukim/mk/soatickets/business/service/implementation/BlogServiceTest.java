package finki.ukim.mk.soatickets.business.service.implementation;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import finki.ukim.mk.soatickets.business.services.IBlogService;
import finki.ukim.mk.soatickets.business.services.INotificationService;
import finki.ukim.mk.soatickets.business.services.implementation.BlogService;
import finki.ukim.mk.soatickets.business.view.models.LookupViewModel;
import finki.ukim.mk.soatickets.business.view.models.blog.AddCommentViewModel;
import finki.ukim.mk.soatickets.business.view.models.blog.CommentViewModel;
import finki.ukim.mk.soatickets.business.view.models.blog.CreatePostViewModel;
import finki.ukim.mk.soatickets.business.view.models.blog.PostViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;
import finki.ukim.mk.soatickets.models.blog.Comment;
import finki.ukim.mk.soatickets.models.blog.Post;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.ICommentRepository;
import finki.ukim.mk.soatickets.repositories.IPostRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link BlogService}
 */
@RunWith(MockitoJUnitRunner.class)
public class BlogServiceTest {

  @Mock
  private IPostRepository postRepository;

  @Mock
  private ICommentRepository commentRepository;

  @Mock
  private IUserRepository userRepository;

  @Mock
  private INotificationService notificationService;

  @InjectMocks
  private IBlogService blogService = new BlogService();


  @Before
  public void setup(){
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
  }

  @Test
  public void shouldGetAllPosts() {
    // When
    List<PostViewModel> posts = blogService.getAllPosts();
    // Then
    PostViewModel post = posts.get(0);

    String content = post.getContent();
    Assert.assertThat(content, Matchers.equalTo("CONTENT"));

    String title = post.getTitle();
    Assert.assertThat(title, Matchers.equalTo("TITLE"));

    String created = post.getCreatedAt();
    Assert.assertThat(created, Matchers.notNullValue());

    LookupViewModel<Long> postAuthor = post.getAuthor();
    String postAuthorName = postAuthor.getName();
    Assert.assertThat(postAuthorName, Matchers.equalTo("FIRST NAME LAST NAME"));
  }

  @Test
  public void shouldGetPostByAuthor() throws Exception {
    // Given
    UserViewModel author = new UserViewModel();
    author.setId(Long.valueOf(2));

    // When
    List<PostViewModel> postsByAuthor = blogService.getPostsByAuthor(author);

    // Then
    PostViewModel post = postsByAuthor.get(0);

    String content = post.getContent();
    Assert.assertThat(content, Matchers.equalTo("CONTENT"));

    String title = post.getTitle();
    Assert.assertThat(title, Matchers.equalTo("TITLE"));

    String created = post.getCreatedAt();
    Assert.assertThat(created, Matchers.notNullValue());

    LookupViewModel<Long> postAuthor = post.getAuthor();
    String postAuthorName = postAuthor.getName();
    Assert.assertThat(postAuthorName, Matchers.equalTo("FIRST NAME LAST NAME"));
  }

  @Test(expected = Exception.class)
  public void shouldGetExceptionWhenTheAuthorNotExists() throws Exception{
    // Given
    UserViewModel author = new UserViewModel();
    author.setId(Long.valueOf(3));
    List<PostViewModel> postsByAuthor = null;
    // When
    try {
      postsByAuthor = blogService.getPostsByAuthor(author);
    } finally {
      // Then
      Assert.assertThat(postsByAuthor, Matchers.nullValue());
    }
  }

  @Test
  public void shouldAddNewPost() throws Exception{
    // Given
    CreatePostViewModel postViewModel = new CreatePostViewModel();
    UserViewModel author = new UserViewModel();
    author.setId(Long.valueOf(2));
    postViewModel.setAuthor(author);
    postViewModel.setContent("content");
    postViewModel.setTitle("title");
    postViewModel.setId(Long.valueOf(1));

    // When
    long addPost = blogService.addPost(postViewModel);
    // Then
    Assert.assertThat(addPost, Matchers.greaterThan(Long.valueOf(0)));
  }

  @Test(expected = Exception.class)
  public void shouldThrowExceptionWhenThereIsNoAuthor() throws Exception{
    // Given
    CreatePostViewModel postViewModel = new CreatePostViewModel();
    UserViewModel author = new UserViewModel();
    author.setId(Long.valueOf(4));
    postViewModel.setAuthor(author);
    postViewModel.setContent("content");
    postViewModel.setTitle("title");
    postViewModel.setId(Long.valueOf(1));
    Long addPost = null;
    // When
    try{
      addPost = blogService.addPost(postViewModel);
    } finally {
      // Then
      Assert.assertThat(addPost, Matchers.nullValue());
    }
  }

  @Test
  public void shouldDeletePost() throws Exception{

    // When
    long deletePost = blogService.deletePost(Long.valueOf(1));
    // Then
    Assert.assertThat(deletePost, Matchers.greaterThan(Long.valueOf(0)));
  }

  @Test(expected = Exception.class)
  public void shouldThrowExceptionWhenPostIsNotFound() throws Exception{

    Long addPost = null;
    // When
    try{
      addPost = blogService.deletePost(Long.valueOf(3));
    } finally {
      // Then
      Assert.assertThat(addPost, Matchers.nullValue());
    }
  }

  @Test
  public void shouldAddNewComment() throws Exception{
    // Given
    AddCommentViewModel commentViewModel = new AddCommentViewModel();
    UserViewModel author = new UserViewModel();
    author.setId(Long.valueOf(2));
    commentViewModel.setAuthor(author);
    commentViewModel.setContent("content");
    commentViewModel.setPostId(Long.valueOf(1));

    // When
    long addComment = blogService.addComment(commentViewModel);
    // Then
    Assert.assertThat(addComment, Matchers.greaterThan(Long.valueOf(0)));
  }

  @Test
  public void shouldDeleteComment() throws Exception{

    // When
    long deletePost = blogService.deleteComment(Long.valueOf(3));
    // Then
    Assert.assertThat(deletePost, Matchers.greaterThan(Long.valueOf(0)));
  }

  @Test(expected = Exception.class)
  public void shouldThrowExceptionWhenCommentIsNotFound() throws Exception{

    Long addPost = null;
    // When
    try{
      addPost = blogService.deleteComment(Long.valueOf(8));
    } finally {
      // Then
      Assert.assertThat(addPost, Matchers.nullValue());
    }
  }

  @Test
  public void shouldGetCommentsForPost() throws Exception {

    // When
    List<CommentViewModel> allCommentsForPost = blogService.getAllCommentsForPost(Long.valueOf(1));

    // Then
    CommentViewModel commentViewModel = allCommentsForPost.get(0);

    LookupViewModel<Long> commentViewModelPost = commentViewModel.getPost();
    String postName = commentViewModelPost.getName();
    Assert.assertThat(postName, Matchers.equalTo("FIRST NAME LAST NAME"));

    String content = commentViewModel.getContent();
    Assert.assertThat(content, Matchers.equalTo("CONTENT"));

    String created = commentViewModel.getCreatedAt();
    Assert.assertThat(created, Matchers.notNullValue());

    LookupViewModel<Long> postAuthor = commentViewModel.getAuthor();
    String postAuthorName = postAuthor.getName();
    Assert.assertThat(postAuthorName, Matchers.equalTo("FIRST NAME LAST NAME"));
  }
}
