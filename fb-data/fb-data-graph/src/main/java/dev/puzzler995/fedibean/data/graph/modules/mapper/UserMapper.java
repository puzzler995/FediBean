package dev.puzzler995.fedibean.data.graph.modules.mapper;

import dev.puzzler995.fedibean.data.graph.model.dto.UserDto;
import dev.puzzler995.fedibean.data.graph.model.node.UserNode;
import dev.puzzler995.fedibean.data.graph.model.node.UserProfileNode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@FunctionalInterface
@Mapper
public interface UserMapper {

  @Mapping(target = "onServerHost", source = "userNode.onServer.host")
  @Mapping(target = "headerUrl", source = "userNode.header.url")
  @Mapping(target = "headerBlurhash", source = "userNode.header.blurhash")
  @Mapping(target = "headerAverageColor", source = "userNode.header.averageColor")
  @Mapping(target = "avatarUrl", source = "userNode.avatar.url")
  @Mapping(target = "avatarBlurhash", source = "userNode.avatar.blurhash")
  @Mapping(target = "avatarAverageColor", source = "userNode.avatar.averageColor")
  @Mapping(target = "id", source = "userNode.id")
  UserDto from(UserNode userNode, UserProfileNode userProfileNode);
}
