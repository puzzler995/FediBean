package dev.puzzler995.fedibean.ui.app.frontend.component;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignSelf;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.Flex;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import dev.puzzler995.fedibean.ui.app.frontend.view.Test2View;
import dev.puzzler995.fedibean.ui.app.frontend.view.TestView;
import java.io.Serial;
import org.vaadin.lineawesome.LineAwesomeIcon;

public class Sidebar extends VerticalLayout {
  @Serial private static final long serialVersionUID = 978242534041635953L;
  private final SideNav sideNav = new SideNav();
  private final SideNavItem test2Item =
      new SideNavItem("test2", Test2View.class, LineAwesomeIcon.ATLASSIAN.create());
  private final SideNavItem testItem =
      new SideNavItem("test", TestView.class, LineAwesomeIcon.HOME_SOLID.create());
  private final Button toggle = new Button(LineAwesomeIcon.CHEVRON_LEFT_SOLID.create());
  private Boolean isSmall = true;

  public Sidebar() {
    testItem.addClassName(AlignSelf.CENTER);
    test2Item.addClassName(AlignSelf.CENTER);
    sideNav.addItem(testItem);
    sideNav.addItem(test2Item);
    sideNav.addClassName(Flex.GROW);
    sideNav.addClassName(FlexDirection.COLUMN);
    sideNav.addClassName(Display.FLEX);
    add(sideNav);

    Footer footer = new Footer();
    Avatar avatar = new Avatar();
    avatar.setImage("https://randomuser.me/api/portraits/women/61.jpg");
    avatar.setWidth("40px");
    avatar.setHeight("40px");
    avatar.addClassName(AlignSelf.CENTER);
    MenuBar profileMenu = new MenuBar();
    profileMenu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY_INLINE);
    MenuItem avatarItem = profileMenu.addItem(avatar);
    SubMenu profileSubMenu = avatarItem.getSubMenu();
    MenuItem profileMenuItem = profileSubMenu.addItem("Profile");
    profileMenuItem.addClickListener(
        e -> profileMenuItem.getUI().ifPresent(ui -> ui.navigate("user/1")));
    profileSubMenu.addItem("Settings");
    profileSubMenu.addItem("Logout");

    // avatar.addClickListener(
    //    e ->
    //        avatar
    //            .getUI()
    //            .ifPresent(ui -> ui.navigate("user/1"))); // TODO: make this the logged in user
    footer.add(profileMenu);

    toggle.addClickListener(this::swapNavSize);
    footer.add(toggle);
    footer.addClassName(AlignSelf.CENTER);
    footer.addClassName(Display.FLEX);
    footer.addClassName(FlexDirection.COLUMN);
    add(footer);

    this.getElement().setAttribute("theme", Lumo.DARK);
  }

  private void swapNavSize(ClickEvent<Button> clickEvent) {
    if (isSmall) {
      testItem.setLabel("test");
      test2Item.setLabel("test2");
      toggle.setIcon(LineAwesomeIcon.CHEVRON_LEFT_SOLID.create());
      isSmall = false;
    } else {
      testItem.setLabel("");
      test2Item.setLabel("");
      toggle.setIcon(LineAwesomeIcon.CHEVRON_RIGHT_SOLID.create());
      isSmall = true;
    }
  }
}
