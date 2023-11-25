package dev.puzzler995.fedibean.federation.spec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resolvable {
  private String id;
  private String type;
}
