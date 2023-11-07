package dev.puzzler995.fedibean.app.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import dev.puzzler995.fedibean.data.model.Note;
import dev.puzzler995.fedibean.data.model.User;
import dev.puzzler995.fedibean.data.model.embeds.NoteReactionType;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@PageTitle("Feed")
@Route(value = "", layout = MainView.class)
public class FeedView extends Div implements AfterNavigationObserver {

  Grid<Note> grid = new Grid<>();

  public FeedView() {
    addClassName("feed-view");
    setSizeFull();
    grid.setHeight("100%");
    grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
    grid.addComponentColumn(note -> createCard(note));
    add(grid);
  }

  @Override
  public void afterNavigation(AfterNavigationEvent event) {
    // TODO Setup Note Paging
    List<Note> demoNotes =
        Arrays.asList(
            createDemoNote(
                "https://randomuser.me/api/portraits/men/42.jpg",
                "John Smith",
                "@johnsmith@mastodon.social",
                "2017-04-01T00:00:00+00:00",
                "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                "20",
                "500",
                "20"),
            createDemoNote(
                "https://randomuser.me/api/portraits/women/42.jpg",
                "Abagail Libbie",
                "@abby@firefish.social",
                "2017-04-01T00:00:00+00:00",
                "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                "10",
                "500",
                "20"),
            createDemoNote(
                "https://randomuser.me/api/portraits/men/24.jpg",
                "Alberto Raya",
                "@ray@techbro.instance",
                "2017-04-01T00:00:00+00:00",
                "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                "1024",
                "500",
                "20"),
            createDemoNote(
                "https://randomuser.me/api/portraits/women/24.jpg",
                "Emmy Elsner",
                "@emmy@girlcock.club",
                "2017-04-01T00:00:00+00:00",
                "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                "35",
                "500",
                "20"),
            createDemoNote(
                "https://randomuser.me/api/portraits/men/76.jpg",
                "Alf Huncoot",
                "@cooter@tooters.org",
                "2017-04-01T00:00:00+00:00",
                "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                "1",
                "500",
                "20"),
            createDemoNote(
                "https://randomuser.me/api/portraits/women/76.jpg",
                "Lidmila Vilensky",
                "@vile@test.org",
                "2017-04-01T00:00:00+00:00",
                "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                "19",
                "500",
                "20"),
            createDemoNote(
                "https://randomuser.me/api/portraits/men/94.jpg",
                "Jarrett Cawsey",
                "@adsgas@mas.to",
                "2017-04-01T00:00:00+00:00",
                "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                "151",
                "500",
                "20"),
            createDemoNote(
                "https://randomuser.me/api/portraits/women/94.jpg",
                "Tania Perfilyeva",
                "@yooot@0w0.is",
                "2017-04-01T00:00:00+00:00",
                "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                "115",
                "500",
                "20"),
            createDemoNote(
                "https://randomuser.me/api/portraits/men/16.jpg",
                "Ivan Polo",
                "@jeel@test.instance",
                "2017-04-01T00:00:00+00:00",
                "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                "2",
                "500",
                "20"),
            createDemoNote(
                "https://randomuser.me/api/portraits/women/16.jpg",
                "Emelda Scandroot",
                "root@root.dev",
                "2017-04-01T00:00:00+00:00",
                "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                "0",
                "500",
                "20"),
            createDemoNote(
                "https://randomuser.me/api/portraits/men/67.jpg",
                "Marcos Sá",
                "@marco@polo.dev",
                "2017-04-01T00:00:00+00:00",
                "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                "3451",
                "500",
                "20"),
            createDemoNote(
                "https://randomuser.me/api/portraits/women/67.jpg",
                "Jacqueline Asong",
                "@jack@fedi.codepenguin.io",
                "2017-04-01T00:00:00+00:00",
                "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                "1151",
                "500",
                "20"));
    grid.setItems(demoNotes);
  }

  private HorizontalLayout createCard(Note note) {
    HorizontalLayout card = new HorizontalLayout();
    card.addClassName("note-card");
    card.setSpacing(false);
    card.getThemeList().add("spacing-s");
    /* Avatar */
    Image image = new Image();
    image.setSrc(String.valueOf(note.getUser().getAvatar()));
    VerticalLayout description = new VerticalLayout();
    description.addClassName("note-description");
    description.setSpacing(false);
    description.setPadding(false);

    /* Header */
    HorizontalLayout header = new HorizontalLayout();
    header.addClassName("note-header");
    header.setSpacing(false);
    header.getThemeList().add("spacing-s");

    Span name = new Span(note.getUser().getName());
    name.addClassName("note-name");
    Span username = new Span(note.getUser().getUsername());
    username.addClassName("note-username");
    Span date = new Span(note.getCreatedAt().toString());
    date.addClassName("note-date");
    header.add(name, username, date);

    Span body = new Span(note.getText());
    body.addClassName("note-body");

    HorizontalLayout actions = new HorizontalLayout();
    actions.addClassName("note-actions");
    actions.setSpacing(false);
    actions.getThemeList().add("spacing-s");

    Icon likeIcon = VaadinIcon.HEART.create();
    likeIcon.addClassName("note-icon");
    Span likeCount = new Span(String.valueOf(note.getReactions().size()));
    likeCount.addClassName("note-like-count");
    Icon commentIcon = VaadinIcon.COMMENT.create();
    commentIcon.addClassName("note-icon");
    Span commentCount = new Span(String.valueOf(note.getReplyCount()));
    commentCount.addClassName("note-comment-count");
    Icon shareIcon = VaadinIcon.SHARE.create();
    shareIcon.addClassName("note-icon");
    Span shareCount = new Span(String.valueOf(note.getBoostCount()));

    actions.add(likeIcon, likeCount, commentIcon, commentCount, shareIcon, shareCount);

    description.add(header, body, actions);
    card.add(image, description);
    return card;
  }

  private Note createDemoNote(
      String image,
      String name,
      String username,
      String date,
      String post,
      String likes,
      String comments,
      String shares) {
    Note note = new Note();
    User user = new User();
    user.setAvatar(URI.create(image));
    user.setName(name);
    user.setUsername(username);

    note.setUser(user);
    note.setCreatedAt(OffsetDateTime.parse(date));
    note.setText(post);
    List<NoteReactionType> likelist = new ArrayList<>();
    for (int i = 0; i < Integer.parseInt(likes); i++) {
      likelist.add(new NoteReactionType());
    }
    note.setReactions(likelist);
    note.setReplyCount(Short.parseShort(comments));
    note.setBoostCount(Short.parseShort(shares));
    return note;
  }
}
