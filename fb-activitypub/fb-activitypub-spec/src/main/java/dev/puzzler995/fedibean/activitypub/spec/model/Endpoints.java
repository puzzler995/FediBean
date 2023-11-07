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
  private URI oauthAuthorizationEndpoint;
  private URI oauthRegistrationEndpoint;
  private URI oauthTokenEndpoint;
  private URI provideClientKey;
  private URI proxyUrl;
  private Collection sharedInbox;
  private URI signClientKey;
  private URI uploadMedia;
}
