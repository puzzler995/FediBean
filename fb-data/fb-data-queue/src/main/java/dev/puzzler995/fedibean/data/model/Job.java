package dev.puzzler995.fedibean.data.model;

import lombok.Data;

import java.net.URI;

@Data
public class Job {
  private String id;
  private URI destination;
  private String payload;
}
