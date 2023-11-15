package dev.puzzler995.fedibean.data.storage.model.constant;

public final class DatabaseConstant {
  public static final String ARTIFACT_COLUMN_ID_NAME = "id";
  public static final String ARTIFACT_COLUMN_TYPE_NAME = "type";
  public static final String ARTIFACT_COLUMN_URI_NAME = "uri";
  public static final String ARTIFACT_TABLE_NAME = "artifact";
  public static final String POSTREACTION_COLUMN_CREATEDAT_NAME = "created_at";
  public static final String POSTREACTION_COLUMN_ID_NAME = "id";
  public static final String POSTREACTION_COLUMN_REACTION_NAME = "reaction";
  public static final String POSTREACTION_TABLE_NAME = "post_reaction";
  public static final String POST_COLUMN_CONTENT_NAME = "content";
  public static final String POST_COLUMN_CREATED_NAME = "created_at";
  public static final String POST_COLUMN_ID_NAME = "id";
  public static final String POST_COLUMN_POSTED_BY_NAME = "user_id";
  public static final String POST_COLUMN_REACTIONS_NAME = "reactions";
  public static final String POST_COLUMN_SUMMARY_NAME = "summary";
  public static final String POST_INVERSEJOINCOLUMNS_JOINCOLUMN_ATTACHMENTS_NAME = "attachments_id";
  public static final String POST_JOINCOLUMNS_JOINCOLUMN_ATTACHMENTS_NAME = "post_id";
  public static final String POST_JOINTABLE_ATTACHMENTS_NAME = "post_attachments";
  public static final String POST_TABLE_NAME = "post";
  public static final String USERACCOUNT_COLUMN_ACCOUNTNONEXPIRED_NAME = "account_non_expired";
  public static final String USERACCOUNT_COLUMN_ACCOUNTNONLOCKED_NAME = "account_non_locked";
  public static final String USERACCOUNT_COLUMN_CREDENTIALSNONEXPIRED_NAME =
      "credentials_non_expired";
  public static final String USERACCOUNT_COLUMN_EMAIL_NAME = "email";
  public static final String USERACCOUNT_COLUMN_ENABLED_NAME = "enabled";
  public static final String USERACCOUNT_COLUMN_ID_NAME = "id";
  public static final String USERACCOUNT_COLUMN_PASSWORD_NAME = "password";
  public static final String USERACCOUNT_TABLE_NAME = "user_account";
  public static final String USER_COLUMN_DISPLAYNAME_NAME = "display_name";
  public static final String USER_COLUMN_ID_NAME = "id";
  public static final String USER_COLUMN_USERNAME_NAME = "username";
  public static final String USER_TABLE_NAME = "usertab";

  private DatabaseConstant() {}
}
