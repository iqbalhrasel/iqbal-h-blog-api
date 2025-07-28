package com.pxc.blog_api.blogs.services;

import com.pxc.blog_api.blogs.dtos.*;
import com.pxc.blog_api.blogs.exceptions.BlogNotFoundException;
import com.pxc.blog_api.blogs.exceptions.TopicNotFoundException;
import com.pxc.blog_api.blogs.mappers.BlogMapper;
import com.pxc.blog_api.blogs.models.Blog;
import com.pxc.blog_api.blogs.models.ContentStatus;
import com.pxc.blog_api.blogs.repositories.BlogRepository;
import com.pxc.blog_api.blogs.repositories.TopicRepository;
import com.pxc.blog_api.iqs.mappers.IqMapper;
import com.pxc.blog_api.iqs.models.InterviewQuestion;
import com.pxc.blog_api.iqs.repositories.IqRepository;
import com.pxc.blog_api.users.UserNotFoundException;
import com.pxc.blog_api.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final IqRepository iqRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final BlogMapper blogMapper;
    private final IqMapper iqMapper;

    public BlogService(BlogRepository blogRepository, IqRepository iqRepository, UserRepository userRepository, TopicRepository topicRepository, BlogMapper blogMapper, IqMapper iqMapper) {
        this.blogRepository = blogRepository;
        this.iqRepository = iqRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.blogMapper = blogMapper;
        this.iqMapper = iqMapper;
    }

    @Value("${perPageSize}")
    private int perPageSize;

    private CompletableFuture<List<Blog>> cfGetLatestBlogsByTopic(int topicId,int limit){
        return CompletableFuture.supplyAsync(()-> blogRepository.getLatestLimitBlogsByTopic(topicId, limit));
    }

    private CompletableFuture<List<Blog>> cfGetLatestBlogs(int limit){
        return CompletableFuture.supplyAsync(()-> blogRepository.getLatestLimitBlogs(limit));
    }

    private CompletableFuture<List<InterviewQuestion>> cfGetLatestIqs(int limit){
        return CompletableFuture.supplyAsync(()-> iqRepository.getLatestLimitIqs(limit));
    }

    public List<BlogCardDto> getLatest3Blogs() {
        var blogs = blogRepository
                .getLatestLimitBlogs(3)
                .stream()
                .map(b -> blogMapper.toCardDto(b))
                .toList();

        return blogs;
    }

    public AllContent getAllContent() {
        var topic = topicRepository.findByName("News and Interests").orElse(null);
        if(topic == null)
            throw new TopicNotFoundException();

        var futureBlogsByTopic = cfGetLatestBlogsByTopic( topic.getId(),3);
        var futureBlogs = cfGetLatestBlogs(4);
        var futureInterviewQs = cfGetLatestIqs(3);

        CompletableFuture.allOf(futureBlogsByTopic, futureBlogs, futureInterviewQs).join();

        var blogsByTopic = futureBlogsByTopic.join();
        var blogs = futureBlogs.join();
        var interviewQs = futureInterviewQs.join();

        var allContent = new AllContent();
        allContent.setNewsInterests( blogsByTopic.stream().map(blogMapper::toCardDto).toList());
        allContent.setLatest4Blogs(blogs.stream().map(blogMapper::toCardDto).toList());
        allContent.setLatest3InterviewQuestion(interviewQs.stream().map(iqMapper::toDto).toList());
        return allContent;
    }

    public BlogDto getBlog(Integer blogId) {
        var blog = blogRepository.getBlogWithAuthorTopic(blogId).orElseThrow(BlogNotFoundException::new);
        return blogMapper.toDto(blog);
    }

    public BlogsWithPage getBlogs(Integer pageNo, Short topicId) {
        if(topicId>0) {
            var topic = topicRepository.findById(topicId).orElse(null);

            if (topic == null)
                throw new TopicNotFoundException();
        }

        int pageIndex ;

        if(pageNo == 1 || pageNo == 0)
            pageIndex = 0;
        else pageIndex = pageNo - 1;

        Pageable pageable = PageRequest.of(pageIndex, perPageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Blog> blogs;

        if(topicId == 0){
            blogs = blogRepository.findByStatus(ContentStatus.PUBLISHED, pageable);
        } else {
            blogs = blogRepository.findByStatusAndTopicId(ContentStatus.PUBLISHED, topicId, pageable);
        }

        var allBlogs = blogs.getContent();
        int totalPages = blogs.getTotalPages();

        var blogsWithPage = new BlogsWithPage();
        blogsWithPage.setBlogs(allBlogs.stream().map(b-> blogMapper.toCardDto(b)).toList());
        blogsWithPage.setTotalPages(totalPages);

        return blogsWithPage;
    }

    public BlogDraftResponse addBlogToDraft(BlogSubmitRequest request) {
        var topic = topicRepository.findById(request.getTopicId()).orElseThrow(TopicNotFoundException::new);

        var blog = new Blog();
        blogMapper.updateEntity(request, blog);
        blog.setTopic(topic);
        blog.setStatus(ContentStatus.DRAFT);

        var userId = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        blog.setAuthor(user);

        blogRepository.save(blog);
        return blogMapper.toDraftResponse(blog);
    }

    public List<BlogDraftResponse> getAllDraftedBlogs() {
        return blogRepository
                .getAllDraftedBlogs()
                .stream()
                .map(b -> blogMapper.toDraftResponse(b))
                .toList();
    }

    public BlogDraftDto getDraftedBlog(Integer blogId) {
        var blog = blogRepository.findById(blogId).orElseThrow(BlogNotFoundException::new);

        return blogMapper.toDraftDto(blog);
    }

    public BlogDraftResponse updateDraftedBlog(Integer blogId, BlogSubmitRequest request) {
        var blog = blogRepository.findById(blogId).orElseThrow(BlogNotFoundException::new);
        var topic = topicRepository.findById(request.getTopicId()).orElseThrow(TopicNotFoundException::new);

        blogMapper.updateEntityWithoutId(request, blog);
        blog.setTopic(topic);

        blogRepository.save(blog);

        return blogMapper.toDraftResponse(blog);
    }

    public void publishDraftedBlogByContent(Integer blogId, BlogSubmitRequest request) {
        var blog = blogRepository.findById(blogId).orElseThrow(BlogNotFoundException::new);
        var topic = topicRepository.findById(request.getTopicId()).orElseThrow(TopicNotFoundException::new);

        blogMapper.updateEntityWithoutId(request, blog);
        blog.setTopic(topic);
        blog.setStatus(ContentStatus.PUBLISHED);
        blog.setCreatedAt(LocalDateTime.now());
        blog.setLastModified(LocalDateTime.now());

        blogRepository.save(blog);
    }

    public void publishDraftedBlogById(Integer blogId) {
        var blog = blogRepository.findById(blogId).orElseThrow(BlogNotFoundException::new);
        blog.setStatus(ContentStatus.PUBLISHED);
        blog.setCreatedAt(LocalDateTime.now());
        blog.setLastModified(LocalDateTime.now());

        blogRepository.save(blog);
    }

    public void deleteBlog(Integer blogId) {
        var blog = blogRepository.findById(blogId).orElseThrow(BlogNotFoundException::new);
        blogRepository.delete(blog);
    }

    public void publishBlog(BlogSubmitRequest request) {
        var topic = topicRepository.findById(request.getTopicId()).orElseThrow(TopicNotFoundException::new);

        var userId = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        var blog = new Blog();
        blogMapper.updateEntity(request, blog);
        blog.setAuthor(user);
        blog.setTopic(topic);
        blog.setStatus(ContentStatus.PUBLISHED);
        blog.setCreatedAt(LocalDateTime.now());
        blog.setLastModified(LocalDateTime.now());


        blogRepository.save(blog);

    }

    public void updatePublishedBlog(Integer blogId, BlogSubmitRequest request) {
        var blog = blogRepository.findById(blogId).orElseThrow(BlogNotFoundException::new);
        var topic = topicRepository.findById(request.getTopicId()).orElseThrow(TopicNotFoundException::new);

        blogMapper.updateEntityWithoutId(request, blog);
        blog.setTopic(topic);
        blog.setLastModified(LocalDateTime.now());

        blogRepository.save(blog);
    }
}
