package dev.puzzler995.fedibean.server.handler.modules.controller.api;

import dev.puzzler995.fedibean.data.graph.model.dto.UserDto;
import dev.puzzler995.fedibean.data.graph.model.node.UserNode;
import dev.puzzler995.fedibean.data.graph.model.node.UserProfileNode;
import dev.puzzler995.fedibean.data.graph.modules.mapper.UserMapper;
import dev.puzzler995.fedibean.data.graph.modules.repository.UserProfileRepository;
import dev.puzzler995.fedibean.data.graph.modules.repository.UserRepository;
import dev.puzzler995.fedibean.server.handler.constant.HandlerConstants.ApiPathConstants;
import dev.puzzler995.fedibean.server.handler.modules.helper.ControllerHelper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(
    path = {ApiPathConstants.USER_API_BASE_PATH},
    produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {
  ControllerHelper helper;
  UserMapper userMapper;
  UserProfileRepository userProfileRepository;
  UserRepository userRepository;

  public UserController(
      ControllerHelper helper,
      UserRepository userRepository,
      UserProfileRepository userProfileRepository,
      UserMapper userMapper) {
    this.helper = helper;
    this.userRepository = userRepository;
    this.userProfileRepository = userProfileRepository;
    this.userMapper = userMapper;
  }

  @GetMapping(path = {ApiPathConstants.ID_PATH})
  public ResponseEntity<UserDto> getUserById(
      @RequestHeader HttpHeaders headers, @PathVariable String id) {
    ResponseEntity<UserDto> response;
    String cacheControl = headers.getCacheControl();
    UserNode userNode = userRepository.findById(id).block();
    UserProfileNode userProfile = userProfileRepository.findByUser_Id(id).block();
    UserDto user = userMapper.from(userNode, userProfile);
    response = helper.buildResponse(user, HttpStatus.OK);

    return response;
  }
}
