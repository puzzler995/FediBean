package dev.puzzler995.fedibean.test.data.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import dev.puzzler995.fedibean.data.graph.model.dto.UserDto;
import dev.puzzler995.fedibean.data.graph.model.node.AssetNode;
import dev.puzzler995.fedibean.data.graph.model.node.ServerNode;
import dev.puzzler995.fedibean.data.graph.model.node.UserNode;
import dev.puzzler995.fedibean.data.graph.model.node.UserProfileNode;
import dev.puzzler995.fedibean.data.graph.modules.mapper.UserMapper;
import java.net.URI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

class UserMapperTest {
  private UserMapper userMapper;
  private UserNode userNode;
  private UserProfileNode userProfileNode;

  @BeforeEach
  public void setup() {
    userMapper = Mappers.getMapper(UserMapper.class);
    userNode = Mockito.mock(UserNode.class);
    userProfileNode = Mockito.mock(UserProfileNode.class);
  }

  @Test
  void testShouldHandleNullValuesInUserNode() {
    // Given
    when(userNode.getOnServer()).thenReturn(null);
    when(userNode.getHeader()).thenReturn(null);
    when(userNode.getAvatar()).thenReturn(null);
    when(userProfileNode.getPronouns()).thenReturn(null);

    // When
    UserDto userDto = userMapper.from(userNode, userProfileNode);

    // Then
    assertNull(userDto.getOnServerHost(), "The Host should be null");
    assertNull(userDto.getHeaderUrl(), "The Header URL should be null");
    assertNull(userDto.getHeaderBlurhash(), "The Header Blurhash should be null");
    assertNull(userDto.getHeaderAverageColor(), "The Header Average Color should be null");
    assertNull(userDto.getAvatarUrl(), "The Avatar URL should be null");
    assertNull(userDto.getAvatarBlurhash(), "The Avatar Blurhash should be null");
    assertNull(userDto.getAvatarAverageColor(), "The Avatar Average Color should be null");
    assertNull(userDto.getPronouns(), "The Pronouns should be null");
  }

  @Test
  void testShouldMapUserNodeToUserDto() {
    // Given
    ServerNode server = Mockito.mock(ServerNode.class);
    AssetNode asset1 = Mockito.mock(AssetNode.class);
    AssetNode asset2 = Mockito.mock(AssetNode.class);
    when(server.getHost()).thenReturn("testHost");
    when(userNode.getOnServer()).thenReturn(server);
    when(userNode.getHeader()).thenReturn(asset1);
    when(userNode.getAvatar()).thenReturn(asset2);
    when(userNode.getOnServer().getHost()).thenReturn("testHost");
    when(userNode.getHeader().getUrl()).thenReturn(URI.create("https://example.com/testHeaderUrl"));
    when(userNode.getHeader().getBlurhash()).thenReturn("testHeaderBlurhash");
    when(userNode.getHeader().getAverageColor()).thenReturn("testHeaderAverageColor");
    when(userNode.getAvatar().getUrl()).thenReturn(URI.create("https://example.com/testAvatarUrl"));
    when(userNode.getAvatar().getBlurhash()).thenReturn("testAvatarBlurhash");
    when(userNode.getAvatar().getAverageColor()).thenReturn("testAvatarAverageColor");
    when(userProfileNode.getPronouns()).thenReturn("she/hers");

    // When
    UserDto userDto = userMapper.from(userNode, userProfileNode);

    // Then
    assertEquals("testHost", userDto.getOnServerHost(), "The Host should be correct");
    assertEquals(
        "https://example.com/testHeaderUrl",
        userDto.getHeaderUrl().toString(),
        "The Header URL should be correct");
    assertEquals(
        "testHeaderBlurhash", userDto.getHeaderBlurhash(), "The Header Blurhash should be correct");
    assertEquals(
        "testHeaderAverageColor",
        userDto.getHeaderAverageColor(),
        "The Header Average Color should be correct");
    assertEquals(
        "https://example.com/testAvatarUrl",
        userDto.getAvatarUrl().toString(),
        "The Avatar URL should be correct");
    assertEquals(
        "testAvatarBlurhash", userDto.getAvatarBlurhash(), "The Avatar Blurhash should be correct");
    assertEquals(
        "testAvatarAverageColor",
        userDto.getAvatarAverageColor(),
        "The Avatar Average Color should be correct");
    assertEquals("she/hers", userDto.getPronouns(), "The Pronouns should be correct");
  }
}
