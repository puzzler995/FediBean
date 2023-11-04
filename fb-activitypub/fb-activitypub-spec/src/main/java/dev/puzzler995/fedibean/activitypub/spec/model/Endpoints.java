package dev.puzzler995.fedibean.activitypub.spec.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.net.URI;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class Endpoints {
  private URI proxyUrl;
  private URI oauthAuthorizationEndpoint;
  private URI oauthTokenEndpoint;
  private URI oauthRegistrationEndpoint;
  private URI uploadMedia;
  private URI provideClientKey;
  private URI signClientKey;
  private Collection sharedInbox;
}
