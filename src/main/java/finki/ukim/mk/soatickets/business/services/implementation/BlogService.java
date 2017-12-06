package finki.ukim.mk.soatickets.business.services.implementation;

import finki.ukim.mk.soatickets.business.services.IBlogService;
import finki.ukim.mk.soatickets.business.view.models.blog.PostViewModel;
import finki.ukim.mk.soatickets.models.blog.Post;
import finki.ukim.mk.soatickets.models.blog.Comment;
import finki.ukim.mk.soatickets.models.user.User;
import finki.ukim.mk.soatickets.repositories.IBlogRepository;
import finki.ukim.mk.soatickets.repositories.ICommentRepository;
import finki.ukim.mk.soatickets.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService implements IBlogService{

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IBlogRepository blogRepository;
    @Autowired
    private ICommentRepository commentRepository;

    private ModelMapper modelMapper;

    public BlogService(){
        modelMapper = new ModelMapper();
    }

    @Override
    public List<PostViewModel> getPublishedPosts() {
        List<PostViewModel> result = new ArrayList<>();
        List<Post> dboPosts = blogRepository.findAllOrderByPublishedAtDesc();
        for(Post post : dboPosts) {
            PostViewModel viewModel = modelMapper.map(post, PostViewModel.class);
            result.add(viewModel);
        }
        return result;
    }

    @Override
    public List<PostViewModel> getPostsByAuthor(User author) {
        List<PostViewModel> result = new ArrayList<>();
        List<Post> dboPosts = blogRepository.findByAuthorOrderByPublishedAtDesc(author);
        for(Post post : dboPosts) {
            PostViewModel viewModel = modelMapper.map(post, PostViewModel.class);
            result.add(viewModel);
        }
        return result;
    }

    @Override
    public List<PostViewModel> searchPosts(String query) {
        List<PostViewModel> result = new ArrayList<>();
        List<Post> dboPosts = blogRepository.findByTitleIgnoreCaseOrderByPublishedAtDesc(query);
        for(Post post : dboPosts) {
            PostViewModel viewModel = modelMapper.map(post, PostViewModel.class);
            result.add(viewModel);
        }
        return result;
    }

    @Override
    public long addPost(String title, String content, String username) throws Exception {

        User author = userRepository.findByUsername(username);

        if (author == null)
            throw new Exception("Author not found!");

        Post post = new Post(title, content, author);
        post.setPublishedAt(new Date());

        return blogRepository.save(post).getId();
    }

    @Override
    public long deletePost(Long postId) throws Exception {
        if (postId < 1)
            throw new Exception("The id has to be greater than 0!");

        blogRepository.delete(postId);
        return postId;
    }

    @Override
    public long addComment(String content, String username, Long postId) throws Exception {

        Post post = blogRepository.findByPostId(postId);

        if (post == null) {
            throw new Exception("The post does not exist or is currently unavailable!");
        }
        Comment comment = new Comment(content, username, post);

        return commentRepository.save(comment).getId();
    }

    @Override
    public long deleteComment(Long commentId) {
        commentRepository.delete(commentId);
        return commentId;
    }
}
