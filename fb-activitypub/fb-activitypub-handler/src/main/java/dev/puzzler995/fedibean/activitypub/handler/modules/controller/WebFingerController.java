package dev.puzzler995.fedibean.activitypub.handler.modules.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/.well-known/webfinger")
public class WebFingerController {

  @GetMapping
  public ResponseEntity<?> getWebFinger(
      @RequestParam("resource") String resource, @RequestHeader HttpHeaders headers) {
    return null;
  }
}
