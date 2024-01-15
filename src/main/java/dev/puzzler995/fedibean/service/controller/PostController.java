package dev.puzzler995.fedibean.service.controller;

import static dev.puzzler995.fedibean.service.ServiceConstants.API_POSTS_PATH;

import dev.puzzler995.fedibean.data.model.PostEntity;
import dev.puzzler995.fedibean.data.modules.repository.PostRepository;
import dev.puzzler995.fedibean.data.modules.repository.UserRepository;
import dev.puzzler995.fedibean.service.model.PostRequest;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_POSTS_PATH)
public class PostController {

  private final PostRepository postRepository;
  private final UserRepository userRepository;

  public PostController(PostRepository postRepository, UserRepository userRepository) {
    this.postRepository = postRepository;
    this.userRepository = userRepository;
  }

  @GetMapping
  public List<PostEntity> getAllPosts(Authentication authentication) {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    return postRepository.findAll();
  }

  @GetMapping("/{id}")
  public PostEntity getPostById(@PathVariable UUID id) {
    return postRepository.findById(id).orElseThrow();
  }

  @PostMapping
  public PostEntity createPost(@RequestBody PostRequest post, Authentication authentication) {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    var builder = PostEntity.builder();
    builder.content(post.getContent());
    var user = userRepository.findByUsername(userDetails.getUsername());
    builder.author(user);
    builder.createdAt(ZonedDateTime.now());
    return postRepository.save(builder.build());
  }
}
