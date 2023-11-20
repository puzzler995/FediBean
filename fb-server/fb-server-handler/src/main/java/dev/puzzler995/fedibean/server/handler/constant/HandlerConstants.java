package dev.puzzler995.fedibean.server.handler.constant;

public final class HandlerConstants {
  private HandlerConstants() {}

  public enum ApiPathConstants {
    ;
    public static final String API_PATH = "api";
    public static final String ID_PATH = "/{id}";
    public static final String USER_PATH = "user";
    public static final String USER_API_BASE_PATH = API_PATH + "/" + USER_PATH;
  }
}
