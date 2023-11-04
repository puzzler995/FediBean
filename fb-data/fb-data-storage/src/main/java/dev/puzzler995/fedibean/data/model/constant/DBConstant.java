package dev.puzzler995.fedibean.data.model.constant;

public final class DBConstant {
  public static final String USER_TABLE_NAME = "user";
  public static final String USER_COLUMN_UPDATEDAT_NAME = "updated_at";
  public static final String USER_COLUMN_LASTFETCHEDAT_NAME = "last_fetched_at";
  public static final String USER_COLUMN_USERNAME_NAME = "username";
  public static final String USER_COLUMN_NAME_NAME = "name";
  public static final String USER_COLUMN_FOLLOWERSCOUNT_NAME = "followers_count";
  public static final String USER_COLUMN_FOLLOWINGCOUNT_NAME = "following_count";
  public static final String USER_COLUMN_NOTESCOUNT_NAME = "notes_count";
  public static final String USER_COLUMN_TAGS_NAME = "tag";
  public static final String USER_COLUMN_ISSUSPENDED_NAME = "is_suspended";
  public static final String USER_COLUMN_ISSILENCED_NAME = "is_silenced";
  public static final String USER_COLUMN_ISLOCKED_NAME = "is_locked";
  public static final String USER_COLUMN_ISBOT_NAME = "is_bot";
  public static final String USER_COLUMN_ISADMIN_NAME = "is_admin";
  public static final String USER_COLUMN_ISMOD_NAME = "is_mod";
  public static final String USER_COLUMN_EMOJI_NAME = "emoji";
  public static final String USER_COLUMN_HOST_NAME = "host";
  public static final String USER_COLUMN_INBOX_NAME = "inbox";
  public static final String USER_COLUMN_SHAREDINBOX_NAME = "shared_inbox";
  public static final String USER_COLUMN_FEATURED_NAME = "featured";
  public static final String USER_COLUMN_URI_NAME = "uri";
  public static final String USER_COLUMN_TOKEN_NAME = "token";
  public static final String USER_COLUMN_ISEXPLORABLE_NAME = "is_explorable";
  public static final String USER_COLUMN_FOLLOWERSURI_NAME = "followers_uri";
  public static final String USER_COLUMN_LASTACTIVEDATE_NAME = "last_active_date";
  public static final String USER_COLUMN_HIDEONLINESTATUS_NAME = "hide_online_status";
  public static final String USER_COLUMN_ISDELETED_NAME = "is_deleted";
  public static final String USER_COLUMN_MOVEDTO_NAME = "moved_to";
  public static final String USER_COLUMN_ALSOKNOWNAS_NAME = "also_known_as";
  public static final String ASSET_TABLE_NAME = "asset";
  public static final String DBITEM_COLUMN_ID_NAME = "id";
  public static final String DBITEM_COLUMN_CREATEDAT_NAME = "created_at";
  public static final String ASSET_COLUMN_MD5_NAME = "md5";
  public static final String ASSET_COLUMN_NAME_NAME = "name";
  public static final String ASSET_COLUMN_TYPE_NAME = "type";
  public static final String ASSET_COLUMN_SIZE_NAME = "size";
  public static final String ASSET_COLUMN_COMMENT_NAME = "comment";
  public static final String ASSET_COLUMN_STOREDINTERNAL_NAME = "stored_internal";
  public static final String ASSET_COLUMN_URL_NAME = "url";
  public static final String ASSET_COLUMN_THUMBNAILURL_NAME = "thumbnail_url";
  public static final String ASSET_COLUMN_WEBPUBLICURL_NAME = "web_public_url";
  public static final String ASSET_COLUMN_ACCESSKEY_NAME = "access_key";
  public static final String ASSET_COLUMN_THUMBNAILACCESSKEY_NAME = "thumbnail_access_key";
  public static final String ASSET_COLUMN_WEBPUBLICACCESSKEY_NAME = "webpublic_access_key";
  public static final String ASSET_COLUMN_URI_NAME = "uri";
  public static final String ASSETFOLDER_TABLE_NAME = "asset_folder";
  public static final String ASSETFOLDER_COLUMN_NAME_NAME = "name";
  public static final String ASSET_COLUMN_ISSENSITIVE_NAME = "is_sensitive";
  public static final String ASSET_COLUMN_ISLINK_NAME = "is_link";
  public static final String ASSET_COLUMN_BLURHASH_NAME = "blurhash";
  public static final String ASSET_COLUMN_WEBPUBLICTYPE_NAME = "web_public_type";
  public static final String ASSET_COLUMN_REQUESTIP_NAME = "request_ip";
  public static final String ABUSEREPORT_TABLE_NAME = "abuse_report";
  public static final String ABUSEREPORT_COLUMN_ISRESOLVED_NAME = "is_resolved";
  public static final String ABUSEREPORT_COLUMN_COMMENT_NAME = "comment";
  public static final String ABUSEREPORT_COLUMN_ISFORWARDED_NAME = "is_forwarded";
  public static final String ANNOUNCEMENT_TABLE_NAME = "announcement";
  public static final String ANNOUNCEMENT_COLUMN_TEXT_NAME = "text";
  public static final String ANNOUNCEMENT_COLUMN_TITLE_NAME = "title";
  public static final String ANNOUNCEMENT_COLUMN_IMAGEURL_NAME = "image_url";
  public static final String ANNOUNCEMENT_COLUMN_UPDATEDAT_NAME = "updated_at";
  public static final String ANNOUNCEMENT_COLUMN_SHOWPOPUP_NAME = "show_popup";
  public static final String ANNOUNCEMENTREAD_TABLE_NAME = "announcement_read";
  public static final String BLOCK_TABLE_NAME = "block";
  public static final String EMOJI_TABLE_NAME = "emoji";
  public static final String EMOJI_COLUMN_UPDATEDAT_NAME = "updated_at";
  public static final String EMOJI_COLUMN_NAME_NAME = "name";
  public static final String EMOJI_COLUMN_HOST_NAME = "host";
  public static final String EMOJI_COLUMN_ORIGINALURL_NAME = "original_url";
  public static final String EMOJI_COLUMN_URI_NAME = "uri";
  public static final String EMOJI_COLUMN_TYPE_NAME = "type";
  public static final String EMOJI_COLUMN_TAGS_NAME = "tag";
  public static final String ASSETPROPERTY_COLUMN_NAME_NAME1 = "name";
  public static final String ASSETPROPERTY_COLUMN_VALUE_NAME1 = "value";
  public static final String REQUESTHEADER_COLUMN_NAME_NAME = "name";
  public static final String REQUESTHEADER_COLUMN_VALUE_NAME = "value";
  public static final String EMOJI_COLUMN_CATEGORY_NAME = "category";
  public static final String EMOJI_COLUMN_PUBLICURL_NAME = "public_url";
  public static final String EMOJI_COLUMN_LICENSE_NAME = "license";
  public static final String EMOJI_COLUMN_WIDTH_NAME = "width";
  public static final String EMOJI_COLUMN_HEIGHT_NAME = "height";
  public static final String FOLLOW_TABLE_NAME = "follow";
  public static final String FOLLOWREQUEST_TABLE_NAME = "follow_request";
  public static final String FOLLOWREQUEST_COLUMN_REQUESTID_NAME = "request_id";
  public static final String SERVER_TABLE_NAME = "server";
  public static final String SERVER_COLUMN_HOST_NAME = "host";
  public static final String SERVER_COLUMN_USERCOUNT_NAME = "user_count";
  public static final String SERVER_COLUMN_NOTECOUNT_NAME = "note_count";
  public static final String SERVER_COLUMN_FOLLOWINGCOUNT_NAME = "following_count";
  public static final String SERVER_COLUMN_FOLLOWERSCOUNT_NAME = "followers_count";
  public static final String SERVER_COLUMN_LASTREQUESTSENT_NAME = "last_request_sent";
  public static final String SERVER_COLUMN_LATESTSTATUS_NAME = "latest_status";
  public static final String SERVER_COLUMN_LASTREQUESTRECEIVED_NAME = "last_request_received";
  public static final String SERVER_COLUMN_LASTCOMMUNICATED_NAME = "last_communicated";
  public static final String SERVER_COLUMN_SOFTWARENAME_NAME = "software_name";
  public static final String SERVER_COLUMN_SOFTWAREVERSION_NAME = "software_version";
  public static final String SERVER_COLUMN_ISOPEN_NAME = "is_open";
  public static final String SERVER_COLUMN_NAME_NAME = "name";
  public static final String SERVER_COLUMN_DESCRIPTION_NAME = "description";
  public static final String SERVER_COLUMN_ADMINNAME_NAME = "admin_name";
  public static final String SERVER_COLUMN_ADMINEMAIL_NAME = "admin_email";
  public static final String SERVER_COLUMN_LASTINFOUPDATE_NAME = "last_info_update";
  public static final String SERVER_COLUMN_ISSUSPENDED_NAME = "is_suspended";
  public static final String SERVER_COLUMN_ICONURL_NAME = "icon_url";
  public static final String SERVER_COLUMN_THEMECOLOR_NAME = "theme_color";
  public static final String SERVER_COLUMN_FAVICONURL_NAME = "favicon_url";
  public static final String MUTE_TABLE_NAME = "mute";
  public static final String MUTE_COLUMN_EXPIRES_NAME = "expires";
  public static final String NOTE_TABLE_NAME = "note";
  public static final String NOTE_COLUMN_TEXT_NAME = "text";
  public static final String NOTE_COLUMN_NAME_NAME = "name";
  public static final String NOTE_COLUMN_SUMMARY_NAME = "summary";
  public static final String NOTE_COLUMN_LOCALONLY_NAME = "local_only";
  public static final String NOTE_COLUMN_BOOSTCOUNT_NAME = "boost_count";
  public static final String NOTE_COLUMN_REPLYCOUNT_NAME = "reply_count";
  public static final String NOTEREACTION_COLUMN_EMOJITEXT_NAME = "emoji_text";
  public static final String NOTEREACTION_COLUMN_COUNT_NAME = "count";
  public static final String NOTE_COLUMN_URI_NAME = "uri";
  public static final String NOTE_COLUMN_SCORE_NAME = "score";
  public static final String ATTACHMENT_COLUMN_FILEID_NAME = "file_id";
  public static final String ATTACHMENT_COLUMN_FILETYPE_NAME = "file_type";
  public static final String NOTE_JOINTABLE_VISIBLETO_NAME = "note_visibleTo";
  public static final String NOTE_JOINCOLUMNS_JOINCOLUMN_VISIBLETO_NAME = "note_id";
  public static final String NOTE_INVERSEJOINCOLUMNS_JOINCOLUMN_VISIBLETO_NAME = "visibleTo_id";
  public static final String NOTE_JOINTABLE_MENTIONS_NAME = "note_users";
  public static final String NOTE_JOINCOLUMNS_JOINCOLUMN_MENTIONS_NAME = "note_id";
  public static final String NOTE_INVERSEJOINCOLUMNS_JOINCOLUMN_MENTIONS_NAME = "users_id";
  public static final String REMOTEUSERREFERENCE_COLUMN_URI_NAME = "uri";
  public static final String REMOTEUSERREFERENCE_COLUMN_URL_NAME = "url";
  public static final String REMOTEUSERREFERENCE_COLUMN_USERNAME_NAME = "username";
  public static final String REMOTEUSERREFERENCE_COLUMN_HOST_NAME = "host";
  public static final String NOTE_JOINTABLE_EMOJIS_NAME = "note_emojis";
  public static final String NOTE_JOINCOLUMNS_JOINCOLUMN_EMOJIS_NAME = "note_id";
  public static final String NOTE_INVERSEJOINCOLUMNS_JOINCOLUMN_EMOJIS_NAME = "emojis_id";
  public static final String NOTE_COLUMN_TAGS_NAME = "tag";
  public static final String NOTE_COLUMN_URL_NAME = "url";
  public static final String NOTE_COLUMN_THREADID_NAME = "thread_id";
  public static final String NOTE_COLUMN_UPDATED_NAME = "updated";
  public static final String NOTE_COLUMN_VISIBILITY_NAME = "visibility";
  public static final String NOTEEDIT_TABLE_NAME = "note_edit";
  public static final String NOTEEDIT_COLUMN_TEXT_NAME = "text";
  public static final String NOTEEDIT_COLUMN_SUMMARY_NAME = "summary";
  public static final String NOTEREACTION_TABLE_NAME = "note_reaction";
  public static final String NOTEREACTION_COLUMN_EMOJITEXT_NAME1 = "emoji_text";
  public static final String POLL_COLUMN_EXPIRES_NAME = "expires";
  public static final String POLL_COLUMN_MULTIPLECHOICE_NAME = "multiple_choice";
  public static final String POLLCHOICE_COLUMN_TEXT_NAME = "text";
  public static final String POLLCHOICE_COLUMN_VOTES_NAME = "votes";
  public static final String POLLVOTE_TABLE_NAME = "poll_vote";
  public static final String POLL_COLUMN_CREATED_NAME = "created";
  public static final String POLLCHOICE_COLUMN_ID_NAME = "id";
  public static final String POLLVOTE_COLUMN_CHOICE_NAME = "choice";
  public static final String USER_COLUMN_AVATAR_NAME = "avatar";

  private DBConstant() {}
}
