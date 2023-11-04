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

@PageTitle("Feed")
@Route(value = "feed", layout = MainView.class)
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

  @Override
  public void afterNavigation(AfterNavigationEvent event) {
    // TODO Setup Note Paging
  }
}
