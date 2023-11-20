package dev.puzzler995.fedibean.server.handler.modules.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ControllerHelper {
  public <T> ResponseEntity<T> buildResponse(T body, HttpStatus status) {
    ResponseEntity<T> response = new ResponseEntity<>(body, status);
    logRequestEnd();
    return response;
  }

  public ResponseEntity<Exception> buildResponse(Exception error) {
    ResponseEntity<Exception> response =
        new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    logRequestError();
    return response;
  }

  private void logRequestEnd() {}

  private void logRequestError() {}

  private void logRequestStart() {}
}
