package dev.puzzler995.fedibean.service.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class PostRequest {
  private String content;

}
