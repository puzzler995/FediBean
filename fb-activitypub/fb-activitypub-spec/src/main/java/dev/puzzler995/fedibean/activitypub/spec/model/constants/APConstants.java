package dev.puzzler995.fedibean.activitypub.spec.model.constants;

import static java.util.Map.entry;

import dev.puzzler995.fedibean.activitypub.spec.model.Activity;
import dev.puzzler995.fedibean.activitypub.spec.model.Actor;
import dev.puzzler995.fedibean.activitypub.spec.model.Collection;
import dev.puzzler995.fedibean.activitypub.spec.model.CollectionPage;
import dev.puzzler995.fedibean.activitypub.spec.model.Link;
import dev.puzzler995.fedibean.activitypub.spec.model.Resolvable;
import dev.puzzler995.fedibean.activitypub.spec.model.activity.Question;
import dev.puzzler995.fedibean.activitypub.spec.model.object.Place;
import dev.puzzler995.fedibean.activitypub.spec.model.object.Profile;
import dev.puzzler995.fedibean.activitypub.spec.model.object.Relationship;
import java.util.Map;

public final class APConstants {
  public static final String NAME_SERVICE = "Service";
  public static final String NAME_LINK = "Link";
  public static final String NAME_ACTOR = "Actor";
  public static final String NAME_COLLECTION = "Collection";
  public static final String NAME_COLLECTION_PAGE = "CollectionPage";
  public static final String NAME_QUESTION = "Question";
  public static final String NAME_RELATIONSHIP = "Relationship";
  public static final String NAME_PLACE = "Place";
  public static final String NAME_PROFILE = "Profile";
  public static final String NAME_ACTIVITY = "Activity";
  public static final String NAME_ORDERED_COLLECTION = "OrderedCollection";
  public static final String NAME_ORDERED_COLLECTION_PAGE = "OrderedCollectionPage";
  public static final String NAME_MENTION = "Mention";
  public static final String NAME_ACCEPT = "Accept";
  public static final String NAME_ADD = "Add";
  public static final String NAME_ANNOUNCE = "Announce";
  public static final String NAME_ARRIVE = "Arrive";
  public static final String NAME_BLOCK = "Block";
  public static final String NAME_CREATE = "Create";
  public static final String NAME_DELETE = "Delete";
  public static final String NAME_DISLIKE = "Dislike";
  public static final String NAME_FLAG = "Flag";
  public static final String NAME_FOLLOW = "Follow";
  public static final String NAME_IGNORE = "Ignore";
  public static final String NAME_INVITE = "Invite";
  public static final String NAME_JOIN = "Join";
  public static final String NAME_LEAVE = "Leave";
  public static final String NAME_LIKE = "Like";
  public static final String NAME_LISTEN = "Listen";
  public static final String NAME_MOVE = "Move";
  public static final String NAME_OFFER = "Offer";
  public static final String NAME_REJECT = "Reject";
  public static final String NAME_READ = "Read";
  public static final String NAME_REMOVE = "Remove";
  public static final String NAME_TENTATIVE_REJECT = "TentativeReject";
  public static final String NAME_TENTATIVE_ACCEPT = "TentativeAccept";
  public static final String NAME_TRAVEL = "Travel";
  public static final String NAME_UNDO = "Undo";
  public static final String NAME_UPDATE = "Update";
  public static final String NAME_VIEW = "View";
  public static final String NAME_APPLICATION = "Application";
  public static final String NAME_GROUP = "Group";
  public static final String NAME_ORGANIZATION = "Organization";
  public static final String NAME_PERSON = "Person";
  public static final String NAME_EMOJI_REACT = "EmojiReact";
  public static final Map<String, String> SPECIAL_METHOD_SUFFIXES =
      Map.of(
          "@context", "JsonContext",
          "_misskey_content", "MisskeyContent",
          "_misskey_quote", "MisskeyQuote",
          "_misskey_reaction", "MisskeyReaction",
          "orderedItems", "Items");
  public static final Map<String, Class<? extends Resolvable>> CLASS_MAP =
      Map.ofEntries(
          entry(NAME_LINK, Link.class),
          entry(NAME_ACTOR, Actor.class),
          entry(NAME_COLLECTION, Collection.class),
          entry(NAME_COLLECTION_PAGE, CollectionPage.class),
          entry(NAME_QUESTION, Question.class),
          entry(NAME_RELATIONSHIP, Relationship.class),
          entry(NAME_PLACE, Place.class),
          entry(NAME_PROFILE, Profile.class),
          entry(NAME_ACTIVITY, Activity.class),
          entry(NAME_ORDERED_COLLECTION, Collection.class),
          entry(NAME_ORDERED_COLLECTION_PAGE, CollectionPage.class),
          entry(NAME_MENTION, Link.class),
          entry(NAME_ACCEPT, Activity.class),
          entry(NAME_ADD, Activity.class),
          entry(NAME_ANNOUNCE, Activity.class),
          entry(NAME_ARRIVE, Activity.class),
          entry(NAME_BLOCK, Activity.class),
          entry(NAME_CREATE, Activity.class),
          entry(NAME_DELETE, Activity.class),
          entry(NAME_DISLIKE, Activity.class),
          entry(NAME_FLAG, Activity.class),
          entry(NAME_FOLLOW, Activity.class),
          entry(NAME_IGNORE, Activity.class),
          entry(NAME_INVITE, Activity.class),
          entry(NAME_JOIN, Activity.class),
          entry(NAME_LEAVE, Activity.class),
          entry(NAME_LIKE, Activity.class),
          entry(NAME_LISTEN, Activity.class),
          entry(NAME_MOVE, Activity.class),
          entry(NAME_OFFER, Activity.class),
          entry(NAME_REJECT, Activity.class),
          entry(NAME_READ, Activity.class),
          entry(NAME_REMOVE, Activity.class),
          entry(NAME_TENTATIVE_REJECT, Activity.class),
          entry(NAME_TENTATIVE_ACCEPT, Activity.class),
          entry(NAME_TRAVEL, Activity.class),
          entry(NAME_UNDO, Activity.class),
          entry(NAME_UPDATE, Activity.class),
          entry(NAME_VIEW, Activity.class),
          entry(NAME_EMOJI_REACT, Activity.class),
          entry(NAME_APPLICATION, Actor.class),
          entry(NAME_GROUP, Actor.class),
          entry(NAME_ORGANIZATION, Actor.class),
          entry(NAME_PERSON, Actor.class),
          entry(NAME_SERVICE, Actor.class));

  private APConstants() {}
}
