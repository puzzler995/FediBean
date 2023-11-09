package dev.puzzler995.fedibean.data.model;

import java.net.URI;
import lombok.Data;

@Data
public class Job {
  private URI destination;
  private String id;
  private String payload;
}
