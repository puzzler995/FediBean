package dev.puzzler995.fedibean.app.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.vaadin.lineawesome.LineAwesomeIcon;

// @Route
public class MainView extends AppLayout {

  public MainView() {
    setPrimarySection(Section.DRAWER);
    addDrawerContent();
  }

  @Override
  protected void afterNavigation() {
    super.afterNavigation();
  }

  private void addDrawerContent() {
    H1 appName = new H1("FediBean");
    appName.addClassNames(FontSize.LARGE, Margin.NONE);
    Header header = new Header(appName);
    Scroller scroller = new Scroller(createNavigation());
    addToDrawer(header, scroller, createFooter());
  }

  private Footer createFooter() {
    Footer footer = new Footer();
    footer.setText("FediBean is a federated social network based on Java and Spring Boot");
    return footer;
  }

  private SideNav createNavigation() {
    SideNav sideNav = new SideNav();
    sideNav.addItem(new SideNavItem("feed", FeedView.class, LineAwesomeIcon.HOME_SOLID.create()));
    //    sideNav.addItem(new SideNavItem("About", AboutView.class,
    // LineAwesomeIcon.INFO_CIRCLE_SOLID));
    return sideNav;
  }
}
