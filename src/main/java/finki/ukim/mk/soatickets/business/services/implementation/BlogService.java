package finki.ukim.mk.soatickets.business.services.implementation;

import finki.ukim.mk.soatickets.business.services.IBlogService;
import finki.ukim.mk.soatickets.business.services.INotificationService;
import finki.ukim.mk.soatickets.business.view.models.LookupViewModel;
import finki.ukim.mk.soatickets.business.view.models.blog.AddCommentViewModel;
import finki.ukim.mk.soatickets.business.view.models.blog.CommentViewModel;
import finki.ukim.mk.soatickets.business.view.models.blog.CreatePostViewModel;
import finki.ukim.mk.soatickets.business.view.models.blog.PostViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.NotificationType;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;
import finki.ukim.mk.soatickets.models.blog.Comment;
import finki.ukim.mk.soatickets.models.blog.Post;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.ICommentRepository;
import finki.ukim.mk.soatickets.repositories.IPostRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DarkoM on 08.12.2017.
 */

@Service
public class BlogService implements IBlogService {
    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private INotificationService notificationService;

    private ModelMapper modelMapper;

    public BlogService(){
        modelMapper = new ModelMapper();
    }

    @Override
    public List<PostViewModel> getAllPosts() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        List<PostViewModel> result = new ArrayList<>();
        for (Post post : postRepository.findAll())
        {
            PostViewModel postViewModel = modelMapper.map(post, PostViewModel.class);
            LookupViewModel<Long> authorViewModel = new LookupViewModel<>(post.getAuthor().getId(), post.getAuthor().toString());
            postViewModel.setAuthor(authorViewModel);
            postViewModel.setCreatedAt(dateFormat.format(post.getCreatedAt()));
            result.add(postViewModel);
        }

        return result;
    }

    @Override
    public List<PostViewModel> getPostsByAuthor(UserViewModel author) throws Exception {
        User user = userRepository.findOne(author.getId());
        if (user == null)
            throw new Exception("User not found!");

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        List<PostViewModel> result = new ArrayList<>();
        for(Post post : user.getPosts()){
            PostViewModel postViewModel = modelMapper.map(post, PostViewModel.class);
            LookupViewModel<Long> authorViewModel = new LookupViewModel<>(post.getAuthor().getId(), post.getAuthor().toString());
            postViewModel.setAuthor(authorViewModel);
            postViewModel.setCreatedAt(dateFormat.format(post.getCreatedAt()));
            result.add(postViewModel);
        }

        return result;
    }

    @Override
    public long addPost(CreatePostViewModel postViewModel) throws Exception {
        User user = userRepository.findOne(postViewModel.getAuthor().getId());
        if (user == null)
            throw new Exception("User not found!");

        Post post = new Post(postViewModel.getTitle(), postViewModel.getContent(), user);
        Post savedPost = postRepository.save(post);

        return savedPost.getId();
    }

    @Override
    public long deletePost(Long postId) throws Exception {
        Post post = postRepository.findOne(postId);
        if (post == null)
            throw new Exception("Post not found!");

        postRepository.delete(post);

        return postId;
    }

    @Override
    public long addComment(AddCommentViewModel commentViewModel) throws Exception {
        User user = userRepository.findOne(commentViewModel.getAuthor().getId());
        if (user == null)
            throw new Exception("User not found!");

        Post post = postRepository.findOne(commentViewModel.getPostId());
        if (post == null)
            throw new Exception("Post not found!");

        Comment comment = new Comment(commentViewModel.getContent(), user, post);
        Comment savedComment = commentRepository.save(comment);

        notificationService.sendNotification(user.getId(), post.getAuthor().getId(), NotificationType.Comment);

        return savedComment.getId();
    }

    @Override
    public long deleteComment(Long commentId) throws Exception {
        Comment comment = commentRepository.findOne(commentId);
        if (comment == null)
            throw new Exception("Comment not found!");

        commentRepository.delete(comment);
        return commentId;
    }

    @Override
    public List<CommentViewModel> getAllCommentsForPost(long postId) throws Exception {
        Post post = postRepository.findOne(postId);
        if (post == null)
            throw new Exception("Post not found!");

        LookupViewModel<Long> postViewModel = new LookupViewModel<>(post.getAuthor().getId(), post.getAuthor().toString());

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        List<CommentViewModel> result = new ArrayList<>();
        for (Comment comment : post.getComments()){
            CommentViewModel commentViewModel = modelMapper.map(post, CommentViewModel.class);
            LookupViewModel<Long> authorViewModel = new LookupViewModel<>(comment.getAuthor().getId(), comment.getAuthor().toString());
            commentViewModel.setAuthor(authorViewModel);

            commentViewModel.setPost(postViewModel);

            commentViewModel.setCreatedAt(dateFormat.format(comment.getCreatedAt()));

            result.add(commentViewModel);
        }

        return result;
    }
}
